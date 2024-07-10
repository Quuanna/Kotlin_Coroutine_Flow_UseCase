package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase6

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.data.PokemonInfo
import com.example.usecase_coroutine_and_test.mock.MockApiService
import com.example.usecase_coroutine_and_test.mock.MockNetworkInterceptor
import com.example.usecase_coroutine_and_test.mock.createMockApi
import com.example.usecase_coroutine_and_test.usecase.BaseViewModel
import com.google.gson.Gson
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import org.mockito.internal.matchers.Null


class CoroutineUseCase6VIewModel(val api: MockApiService = mockApi()) : BaseViewModel<Null>() {

    val pokemonInfoList: LiveData<List<PokemonInfo>> get() = _pokemonInfoList
    private val _pokemonInfoList = MutableLiveData<List<PokemonInfo>>()

    fun networkPerformRetryAndTimeout() {
        uiState.value = UiState.Loading
        val numberOfRetries = 2
        val timeout = 1000L

        val deferredOne = viewModelScope.async {
            retryWithTimeout(numberOfRetries, timeout) {
                api.getPokemonInfoCode(1)
            }
        }
        val deferredTwo = viewModelScope.async {
            retryWithTimeout(numberOfRetries, timeout) {
                api.getPokemonInfoCode(2)
            }
        }

        viewModelScope.launch {
            try {
                _pokemonInfoList.value = listOf(deferredOne, deferredTwo).awaitAll()
                uiState.value = UiState.Success
            } catch (e: Exception) {
                uiState.value = UiState.Error("Network Request failed")
            }
        }
    }


    private suspend fun <T> retryWithTimeout(
        numberOfRetries: Int,
        timeout: Long,
        block: suspend () -> T
    ) = retry(numberOfRetries) {
        withTimeout(timeout) {
            block()
        }
    }

    private suspend fun <T> retry(
        numberOfRetries: Int,
        delayBetweenRetries: Long = 100,
        block: suspend () -> T
    ): T {
        repeat(numberOfRetries) {
            try {
                return block()
            } catch (e: Exception) {
                uiState.value = UiState.Error(e.message.toString())
            }
            delay(delayBetweenRetries)
        }
        return block() // last attempt
    }
}

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        // timeout on first request for oreo features
        .mock(
            "http://localhost/pokemon/1",
            { response(1) },
            200,
            1050,
            persist = false
        )
        // network error on second request
        .mock(
            "http://localhost/pokemon/1",
            { "Something went wrong on servers side" },
            500,
            100,
            persist = false
        )
        // 3rd request is successful and within timeout
        .mock(
            "http://localhost/pokemon/1",
            { response(1) },
            200,
            100
        )
        // timeout on first request for pie features
        .mock(
            "http://localhost/pokemon/2",
            { response(2) },
            200,
            1050,
            persist = false
        )
        // network error on second request
        .mock(
            "http://localhost/pokemon/2",
            { "Something went wrong on servers side" },
            500,
            100,
            persist = false
        )
        // 3rd request is successful and within timeout
        .mock(
            "http://localhost/pokemon/2",
            { response(2) },
            200,
            100
        )
)

private fun response(code: Int): String = Gson().toJson(
    PokemonInfo(
        "pokemon",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${code}.png"
    )
)