package com.anna.usecase_coroutine_and_test.data.repo.dataSource

import com.anna.usecase_coroutine_and_test.core.api.PokemonService
import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRemoteDataSourceImpl(
    private val apiService: PokemonService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): PokemonRemoteDataSource  {

    override suspend fun getPokemonInfo(page: Int): PokemonInfo? {
        var pokemonInfo: PokemonInfo? = null
        withContext(ioDispatcher) {
            val name = apiService.getPokemonList(page).results.first().name
            if (name.isNotEmpty()) {
                val image = apiService.getPokemonInfo(name).sprites.other.home.frontDefault
                if (image.isNotEmpty()) {
                    pokemonInfo = PokemonInfo(name, image)
                }
            }
        }
        return pokemonInfo
    }

}