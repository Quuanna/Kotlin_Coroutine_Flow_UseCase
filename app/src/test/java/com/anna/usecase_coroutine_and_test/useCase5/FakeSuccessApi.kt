package com.example.usecase_coroutine_and_test.useCase5

import com.anna.usecase_coroutine_and_test.data.PokemonInfo
import com.anna.usecase_coroutine_and_test.mock.MockApiService

class FakeSuccessApi(val responseDelay: Long) : MockApiService {

    override suspend fun getPokemonInfo(): PokemonInfo {
        return PokemonInfo(
            "pokemon",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        )
    }
}