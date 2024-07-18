package com.anna.usecase_coroutine_and_test.data.repo.dataSource

import com.anna.usecase_coroutine_and_test.data.local.PokemonInfoEntity

interface PokemonLocalDataSource {
    suspend fun getPokemonInfo(): PokemonInfoEntity
    suspend fun insertData(pokemonInfoEntity: PokemonInfoEntity)
    suspend fun clearDatabase()
}