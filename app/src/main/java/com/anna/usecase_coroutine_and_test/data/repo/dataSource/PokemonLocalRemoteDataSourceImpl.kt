package com.anna.usecase_coroutine_and_test.data.repo.dataSource

import com.anna.usecase_coroutine_and_test.data.local.PokemonInfoDao
import com.anna.usecase_coroutine_and_test.data.local.PokemonInfoEntity
import com.anna.usecase_coroutine_and_test.data.local.asExternalModel
import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonLocalRemoteDataSourceImpl(
    private val dataBase: PokemonInfoDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokemonLocalDataSource {

    override suspend fun getPokemonInfo(): PokemonInfoEntity = withContext(ioDispatcher) {
        dataBase.getPokemonInfo()
    }

    override suspend fun insertData(pokemonInfoEntity: PokemonInfoEntity) {
        withContext(ioDispatcher) {
            dataBase.insert(pokemonInfoEntity)
        }
    }

    override suspend fun clearDatabase() = dataBase.clear()


}