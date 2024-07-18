package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase8

import androidx.lifecycle.viewModelScope
import com.anna.usecase_coroutine_and_test.constant.UiState
import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import com.anna.usecase_coroutine_and_test.mock.api.MockApiService
import com.anna.usecase_coroutine_and_test.mock.api.MockNetworkInterceptor
import com.anna.usecase_coroutine_and_test.mock.api.createMockApi
import com.anna.usecase_coroutine_and_test.usecase.BaseViewModel
import com.google.gson.Gson
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CoroutineUseCase8ViewModel(
    private val api: MockApiService = mockApi()
) : BaseViewModel<PokemonInfo>() {


    fun handleExceptionWithTryCatch() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                api.getPokemonInfoCode(2)
            } catch (e: Exception) {
                uiState.value = UiState.Error("Network Request failed: $e")
            }
        }
    }

    fun handleWithCoroutineExceptionHandler() {
        uiState.value = UiState.Loading
        viewModelScope.launch(coroutineException) {
            val deferred = viewModelScope.async { api.getPokemonInfoCode(2) }
            uiState.value = UiState.Success
            pokemonInfo.value = deferred.await()
        }
    }
}

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/pokemon/1",
            {
                Gson().toJson(
                    PokemonInfo(
                        "pokemon",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
                    )
                )
            },
            200,
            1000
        )
        .mock(
            "http://localhost/pokemon/2",
            { "Something went wrong on servers side" },
            500,
            100
        )
)

