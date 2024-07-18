package com.anna.usecase_coroutine_and_test.data.repo.dataSource

import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo


interface PokemonRemoteDataSource {
    suspend fun getPokemonInfo(page: Int): PokemonInfo?
}