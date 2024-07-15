package com.anna.usecase_coroutine_and_test.data.repo.dataSource

import com.anna.usecase_coroutine_and_test.data.local.PokemonInfoDao
import com.anna.usecase_coroutine_and_test.data.local.PokemonInfoEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonLocalDataSource(
    private val dataBase: PokemonInfoDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getPokemonInfo(): PokemonInfoEntity = withContext(ioDispatcher) {
        dataBase.getPokemonInfo()
    }

    suspend fun insertData(pokemonInfoEntity: PokemonInfoEntity) {
        withContext(ioDispatcher) {
            dataBase.insert(pokemonInfoEntity)
        }
    }

    suspend fun clearDatabase() = dataBase.clear()


}