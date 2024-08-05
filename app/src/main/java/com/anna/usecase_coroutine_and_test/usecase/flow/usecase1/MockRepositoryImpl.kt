package com.anna.usecase_coroutine_and_test.usecase.flow.usecase1

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
        val current = mockApi.getPokemonInfoCode(2) // 1 success、2 Fail
        emit(current)
        // 模擬時間
        delay(5_000)
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


