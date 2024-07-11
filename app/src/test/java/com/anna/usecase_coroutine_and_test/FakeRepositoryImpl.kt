package com.example.usecase_coroutine_and_test

import com.anna.usecase_coroutine_and_test.core.api.PokemonService
import com.anna.usecase_coroutine_and_test.core.model.response.PokemonInfoResponse
import com.anna.usecase_coroutine_and_test.core.model.response.PokemonListResponse
import com.anna.usecase_coroutine_and_test.repo.PokemonRepository

class FakeRepositoryImpl(val fakeApi: PokemonService) : PokemonRepository {
    private val PAGING_SIZE = 20

    override suspend fun fetchPokemonList(page: Int): Result<PokemonListResponse> {
        return runCatching {
            fakeApi.getPokemonList(PAGING_SIZE, page * PAGING_SIZE)
        }
    }

    override suspend fun fetchPokemonInfo(name: String): Result<PokemonInfoResponse> {
        return runCatching {
            fakeApi.getPokemonInfo(name)
        }
    }
}