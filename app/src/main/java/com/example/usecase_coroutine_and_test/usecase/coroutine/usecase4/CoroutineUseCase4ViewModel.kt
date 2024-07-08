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
//         usingWithTimeout(timeout = timeout)
         usingWithTimeoutOrNull(timeout= timeout)
    }

    private fun usingWithTimeout(page: Int = 1,timeout: Long) {
        viewModelScope.launch {
            try {
                withTimeout(timeout) {
                    getPokemonList(page) { name ->
                        pokemonInfo.value = PokemonInfo(
                            name,
                            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
                        )
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
        viewModelScope.launch {
            try {
                val pokemon = withTimeoutOrNull(timeout) {
                    getPokemonList(page) { name ->
                        pokemonInfo.value = PokemonInfo(
                            name,
                            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
                            )
                    }
                }

                if (pokemon != null) {
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

}