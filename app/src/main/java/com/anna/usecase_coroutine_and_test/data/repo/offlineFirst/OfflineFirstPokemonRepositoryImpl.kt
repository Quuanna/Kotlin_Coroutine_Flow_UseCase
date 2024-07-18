package com.anna.usecase_coroutine_and_test.data.repo.offlineFirst

import com.anna.usecase_coroutine_and_test.data.local.PokemonInfoEntity
import com.anna.usecase_coroutine_and_test.data.local.asEntity
import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import com.anna.usecase_coroutine_and_test.data.repo.dataSource.PokemonLocalDataSource
import com.anna.usecase_coroutine_and_test.data.repo.dataSource.PokemonRemoteDataSource

class OfflineFirstPokemonRepositoryImpl(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource,
) : OfflineFirstPokemonRepository {

    override suspend fun networkRequestFetch(page: Int): PokemonInfo? {
        val response = remoteDataSource.getPokemonInfo(page)
        return if (response != null) {
            localDataSource.insertData(response.asEntity())
            remoteDataSource.getPokemonInfo(page)
        } else {
            null
        }
    }

    override suspend fun localDataPokemonInfo(): PokemonInfoEntity {
        return localDataSource.getPokemonInfo()
    }

    override suspend fun localDatabaseClear() {
        localDataSource.clearDatabase()
    }

}