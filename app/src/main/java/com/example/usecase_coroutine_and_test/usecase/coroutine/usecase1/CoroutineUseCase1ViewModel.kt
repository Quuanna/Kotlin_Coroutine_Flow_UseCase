package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase1

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


class CoroutineUseCase1ViewModel(private val repository: PokemonRepository) :
    BaseViewModel<UiState>() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CoroutineUseCase1ViewModel(repository = PokemonRepositoryImpl(PokemonCline.apiService))
            }
        }
    }

    fun getPokemonName(page: Int) {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            val list = repository.fetchPokemonList(page)
            try {
                uiState.value = UiState.Success
                pokemonInfo.value = PokemonInfo(
                    name = list.getOrThrow().results.first().name,
                    imageUrl = ""
                )
            } catch (e: Exception) {
                uiState.value = UiState.Error()
            }
        }
    }

}