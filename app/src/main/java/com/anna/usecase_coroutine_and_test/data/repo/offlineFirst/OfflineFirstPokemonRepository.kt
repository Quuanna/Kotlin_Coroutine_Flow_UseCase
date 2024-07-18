package com.anna.usecase_coroutine_and_test.data.repo.offlineFirst

import com.anna.usecase_coroutine_and_test.data.local.PokemonInfoEntity
import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo

interface OfflineFirstPokemonRepository {
    suspend fun networkRequestFetch(page: Int): PokemonInfo?
    suspend fun localDataPokemonInfo(): PokemonInfoEntity?
    suspend fun localDatabaseClear()
}