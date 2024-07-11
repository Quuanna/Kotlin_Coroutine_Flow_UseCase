package com.anna.usecase_coroutine_and_test.useCase5

import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import com.anna.usecase_coroutine_and_test.mock.api.MockApiService

class FakeSuccessApi(val responseDelay: Long) : MockApiService {

    override suspend fun getPokemonInfo(): PokemonInfo {
        return PokemonInfo(
            "pokemon",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        )
    }

    override suspend fun getPokemonInfoCode(code: Int): PokemonInfo {
        return PokemonInfo(
            "pokemon",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$code.png"
        )
    }
}