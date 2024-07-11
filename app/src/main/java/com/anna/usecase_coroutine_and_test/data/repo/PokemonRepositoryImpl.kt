package com.anna.usecase_coroutine_and_test.data.repo

import com.anna.usecase_coroutine_and_test.core.api.PokemonService
import com.anna.usecase_coroutine_and_test.data.network.models.PokemonInfoResponse
import com.anna.usecase_coroutine_and_test.data.network.models.PokemonListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PokemonRepositoryImpl(private val apiService: PokemonService) : PokemonRepository {

    companion object {
        private const val PAGING_SIZE = 20
    }

    override suspend fun fetchPokemonList(page: Int): Result<PokemonListResponse> =
        withContext(Dispatchers.IO) {
            runCatching {
                apiService.getPokemonList(
                    limit = PAGING_SIZE,
                    offset = page * PAGING_SIZE
                )
            }
        }

    override suspend fun fetchPokemonInfo(name: String): Result<PokemonInfoResponse> =
        withContext(Dispatchers.IO) {
            runCatching {
                apiService.getPokemonInfo(name)
            }
        }

}