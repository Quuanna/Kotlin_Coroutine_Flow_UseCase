package com.anna.usecase_coroutine_and_test.data.repo.offlineFirst

import com.anna.usecase_coroutine_and_test.data.local.PokemonInfoEntity
import com.anna.usecase_coroutine_and_test.data.local.asEntity
import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import com.anna.usecase_coroutine_and_test.data.repo.dataSource.PokemonLocalDataSource
import com.anna.usecase_coroutine_and_test.data.repo.dataSource.PokemonRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class OfflineFirstPokemonAsyncRepositoryImpl(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource,
    private val scope: CoroutineScope,
) : OfflineFirstPokemonRepository {

    override suspend fun networkRequestFetch(page: Int): PokemonInfo? {
        return scope.async {
            delay(1000)
            val response = remoteDataSource.getPokemonInfo(page)
            if (response != null) {
                localDataSource.insertData(response.asEntity())
                remoteDataSource.getPokemonInfo(page)
            } else {
                null
            }
        }.await()
    }

    override suspend fun localDataPokemonInfo(): PokemonInfoEntity {
        return localDataSource.getPokemonInfo()
    }

    override suspend fun localDatabaseClear() {
        localDataSource.clearDatabase()
    }
}