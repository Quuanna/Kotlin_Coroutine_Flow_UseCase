package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase5


import androidx.lifecycle.viewModelScope
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.data.PokemonInfo
import com.example.usecase_coroutine_and_test.mock.MockApiService
import com.example.usecase_coroutine_and_test.mock.MockNetworkInterceptor
import com.example.usecase_coroutine_and_test.mock.createMockApi
import com.example.usecase_coroutine_and_test.usecase.BaseViewModel
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineUseCase5ViewModel(val api: MockApiService = mockApi()) : BaseViewModel<PokemonInfo>() {

    fun performNetworkRequest() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            val numberRetries = 2
            try {
                retry(numberOfRetries= numberRetries) {
                    val pokemon = api.getPokemonInfo()
                    pokemonInfo.value = PokemonInfo(name = pokemon.name, imageUrl = pokemon.imageUrl)
                    uiState.value = UiState.Success
                }

            } catch (e: Exception) {
                uiState.value = UiState.Error("Network Request failed")
            }
        }
    }


    private suspend fun <T> retry(
        numberOfRetries: Int,
        delayBetweenRetries:Long = 100,
        maxDelayMillis: Long = 1000,
        factor:Double = 2.0, // retry with exponential backoff
        block: suspend () -> T
    ): T {
        var currentDelay = delayBetweenRetries
        repeat(numberOfRetries) {
            try {
                return block()
            } catch (_: Exception) {
            }
            delay(currentDelay)
            currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelayMillis)
        }

        return block() // last attempt
    }
}

fun mockApi() = createMockApi(
    MockNetworkInterceptor().mock(
        "http://localhost/pokemon",
        { "something went wrong on server side" },
        500,
        1000,
        persist = false
    ).mock(
        "http://localhost/pokemon",
        { "something went wrong on server side" },
        500,
        1000,
        persist = false
    ).mock(
        "http://localhost/pokemon",
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
)