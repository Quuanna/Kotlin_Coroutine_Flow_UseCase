package com.anna.usecase_coroutine_and_test.usecase.flow.usecase3

import android.util.Log
import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import com.anna.usecase_coroutine_and_test.mock.api.MockApiService
import com.anna.usecase_coroutine_and_test.mock.api.MockNetworkInterceptor
import com.anna.usecase_coroutine_and_test.mock.api.createMockApi
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class MockRepositoryImpl(
    private val mockApi: MockApiService
) : MockRepository {

    override fun fetchPokemon(): Flow<PokemonInfo> = flow {
        while(true) { // 模擬第二次觸發network request fail 失敗
            Log.d("TEST", "Fetching current Pokemon")
            val current = mockApi.getPokemonInfoCode(1)
            emit(current)
            // 模擬時間
            delay(5_000)
        }
    }.retry { cause ->
        Log.d("TEST", "Enter retry operator with $cause")
        val shouldRetry = cause is Exception

        if (shouldRetry) {
            delay(5_000)
        }

        //  retryWhen
        return@retry shouldRetry
    }
}

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            path = "http://localhost/pokemon/1",
            body = {
                Gson().toJson(
                    PokemonInfo(
                        "pokemon",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
                    )
                )
            },
            status = 200,
            delayInMs = 1500,
            errorFrequencyInPercent = 50 // 透過此設定去改變 Status code 為 500 觸發 retry
        )
)

