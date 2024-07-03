package com.example.usecase_coroutine_and_test.useCase

import com.example.usecase_coroutine_and_test.core.api.PokemonService
import com.example.usecase_coroutine_and_test.core.model.response.PokemonInfoResponse
import com.example.usecase_coroutine_and_test.core.model.response.PokemonListResponse
import com.example.usecase_coroutine_and_test.repo.PokemonRepository

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