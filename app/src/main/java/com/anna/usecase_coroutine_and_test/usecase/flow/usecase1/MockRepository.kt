package com.anna.usecase_coroutine_and_test.usecase.flow.usecase1

import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface MockRepository {
     fun fetchPokemon(): Flow<PokemonInfo>
}