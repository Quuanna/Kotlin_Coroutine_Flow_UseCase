package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.usecase_coroutine_and_test.constant.UiStates
import com.example.usecase_coroutine_and_test.core.api.PokemonCline
import com.example.usecase_coroutine_and_test.core.model.response.PokemonInfoResponse
import com.example.usecase_coroutine_and_test.core.model.response.PokemonListResponse
import com.example.usecase_coroutine_and_test.repo.PokemonRepository
import com.example.usecase_coroutine_and_test.repo.PokemonRepositoryImpl
import com.example.usecase_coroutine_and_test.usecase.BaseViewModel
import kotlinx.coroutines.launch

// 將資料取得邏輯與 UI 邏輯分離
class CoroutineUseCase1ViewModel(private val repo: PokemonRepository) : BaseViewModel<UiStates>() {

    private val _pokemonList = MutableLiveData<List<PokemonListResponse>>()
    val pokemonList: LiveData<List<PokemonListResponse>> = _pokemonList

    private val _pokemonInfo = MutableLiveData<PokemonInfoResponse>()
    val pokemonInfo: LiveData<PokemonInfoResponse> = _pokemonInfo

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CoroutineUseCase1ViewModel(repo = PokemonRepositoryImpl(PokemonCline.apiService))
            }
        }
    }

    fun getPokemonList(page: Int) {
        viewModelScope.launch {
            uiState.value = UiStates.Loading
            val result = repo.fetchPokemonList(page)
            if (result.isSuccess) {
                uiState.value = UiStates.Success("Success")
            } else {
                uiState.value = UiStates.Error("error")
            }
        }
    }

    fun getPokemonInfo(name: String) {
        viewModelScope.launch {
            uiState.value = UiStates.Loading
            val result = repo.fetchPokemonInfo(name)
            if (result.isSuccess) {
                uiState.value = UiStates.Success("Success")
            } else {
                uiState.value = UiStates.Error("error")
            }
        }
    }

}