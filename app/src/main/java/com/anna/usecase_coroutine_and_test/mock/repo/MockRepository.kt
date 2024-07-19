package com.anna.usecase_coroutine_and_test.mock.repo

import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface MockRepository {
     fun fetchPokemon(): Flow<PokemonInfo>
}