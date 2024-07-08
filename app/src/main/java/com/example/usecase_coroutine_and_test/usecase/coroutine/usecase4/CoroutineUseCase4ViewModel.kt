package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase4

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.core.api.PokemonCline
import com.example.usecase_coroutine_and_test.data.PokemonInfo
import com.example.usecase_coroutine_and_test.repo.PokemonRepository
import com.example.usecase_coroutine_and_test.repo.PokemonRepositoryImpl
import com.example.usecase_coroutine_and_test.usecase.BaseViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

class CoroutineUseCase4ViewModel(val repository: PokemonRepository) : BaseViewModel<PokemonInfo>() {

    companion object {
        val Factory = viewModelFactory {
            initializer {
                CoroutineUseCase4ViewModel(repository = PokemonRepositoryImpl(PokemonCline.apiService))
            }
        }
    }

    fun performNetworkRequest(timeout: Long) {
        uiState.value = UiState.Loading
        // usingWithTimeout(timeout)
        usingWithTimeoutOrNull(timeout= timeout)
    }

    private fun usingWithTimeout(page: Int = 1,timeout: Long) {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val recentVersions = withTimeout(timeout) {
                    getPokemonList(page) { name ->
                        getPokemonInfo(name) { imageUrl ->
                            pokemonInfo.value = PokemonInfo(name, imageUrl)
                            uiState.value = UiState.Success
                        }
                    }
                }
                uiState.value = UiState.Success
            } catch (timeoutCancellationException: TimeoutCancellationException) {
                uiState.value = UiState.Error("Network Request timed out!")
            } catch (exception: Exception) {
                uiState.value = UiState.Error("Network Request failed!")
            }
        }
    }


    private fun usingWithTimeoutOrNull(page: Int = 1, timeout: Long) {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val recentVersions = withTimeoutOrNull(timeout) {
                    getPokemonList(page) { name ->
                        getPokemonInfo(name) { imageUrl ->
                            pokemonInfo.value = PokemonInfo(name, imageUrl)
                            uiState.value = UiState.Success
                        }
                    }
                }

                if (recentVersions != null) {
                    uiState.value = UiState.Success
                } else {
                    uiState.value = UiState.Error("Network Request timed out!")
                }
            } catch (exception: Exception) {
                uiState.value = UiState.Error("Network Request failed!")
            }
        }
    }



    private suspend  fun getPokemonList(page: Int, callback: (String) -> Unit) {
        repository.fetchPokemonList(page).run {
            if (isSuccess) {
                callback(getOrThrow().results.first().name)
            } else {
                uiState.value = UiState.Error()
            }
        }
    }

    private fun getPokemonInfo(name: String, callback: (String) -> Unit) {
        viewModelScope.launch {
            repository.fetchPokemonInfo(name).run {
                if (isSuccess) {
                    callback(getOrThrow().sprites.other.home.frontDefault)
                } else {
                    uiState.value = UiState.Error()
                }
            }
        }
    }
}