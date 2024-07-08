package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase2


import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.core.api.PokemonCline
import com.example.usecase_coroutine_and_test.data.PokemonInfo
import com.example.usecase_coroutine_and_test.repo.PokemonRepository
import com.example.usecase_coroutine_and_test.repo.PokemonRepositoryImpl
import com.example.usecase_coroutine_and_test.usecase.BaseViewModel
import kotlinx.coroutines.launch

class CoroutineUseCase2ViewModel(val repository: PokemonRepository) : BaseViewModel<PokemonInfo>() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CoroutineUseCase2ViewModel(repository = PokemonRepositoryImpl(PokemonCline.apiService))
            }
        }
    }

    fun getPokemonImage(page: Int) {
        uiState.value = UiState.Loading
        getPokemonList(page) { name ->
            try {
                getPokemonInfo(name) { imageUrl ->
                    pokemonInfo.value = PokemonInfo(name, imageUrl)
                    uiState.value = UiState.Success
                }
            } catch (e: Exception) {
                uiState.value = UiState.Error(e.message.toString())
            }
        }
    }

    private fun getPokemonList(page: Int, name: (String) -> Unit) {
        viewModelScope.launch {
            repository.fetchPokemonList(page).run {
                if (isSuccess) {
                    name.invoke(getOrThrow().results.first().name)
                } else {
                    uiState.value = UiState.Error()
                }
            }
        }
    }

    private fun getPokemonInfo(name: String, imageUrl: (String) -> Unit) {
        viewModelScope.launch {
            repository.fetchPokemonInfo(name).run {
                if (isSuccess) {
                    imageUrl.invoke(getOrThrow().sprites.other.home.frontDefault)
                } else {
                    uiState.value = UiState.Error()
                }
            }
        }
    }

}