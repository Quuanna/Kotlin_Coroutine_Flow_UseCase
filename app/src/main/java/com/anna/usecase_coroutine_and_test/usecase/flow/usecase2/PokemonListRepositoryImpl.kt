package com.anna.usecase_coroutine_and_test.usecase.flow.usecase2

import com.anna.usecase_coroutine_and_test.core.api.PokemonService
import com.anna.usecase_coroutine_and_test.data.network.models.PokemonListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonListRepositoryImpl(private val apiService: PokemonService): PokemonListRepository {

    override val fetchPokemon: Flow<List<PokemonListResponse.Result>> = flow {
        val list = apiService.getPokemonList()
        emit(list.results)
    }
}