package com.anna.usecase_coroutine_and_test.usecase.flow.usecase4

import com.anna.usecase_coroutine_and_test.data.network.models.PokemonListResponse.Result
import kotlinx.coroutines.flow.Flow

interface PokemonListRepository {
    val fetchPokemon: Flow<List<Result>>
}