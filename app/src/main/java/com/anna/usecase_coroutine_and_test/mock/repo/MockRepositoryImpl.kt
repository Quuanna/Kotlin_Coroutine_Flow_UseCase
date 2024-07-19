package com.anna.usecase_coroutine_and_test.mock.repo

import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import com.anna.usecase_coroutine_and_test.mock.api.MockApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class MockRepositoryImpl(
    private val mockApi: MockApiService
) : MockRepository {

    override fun fetchPokemon(): Flow<PokemonInfo> = flow {
        val current = mockApi.getPokemonInfoCode(2)
        emit(current)
        // 模擬時間
        delay(5_000)
    }
}


