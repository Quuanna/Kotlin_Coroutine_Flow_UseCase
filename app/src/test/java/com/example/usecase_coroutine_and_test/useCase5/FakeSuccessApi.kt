package com.example.usecase_coroutine_and_test.useCase5

import com.example.usecase_coroutine_and_test.core.api.PokemonService
import com.example.usecase_coroutine_and_test.core.model.response.PokemonInfoResponse
import com.example.usecase_coroutine_and_test.core.model.response.PokemonListResponse
import com.example.usecase_coroutine_and_test.data.PokemonInfo
import com.example.usecase_coroutine_and_test.usecase.coroutine.usecase5.MockApiService
import com.google.gson.Gson
import kotlinx.coroutines.delay

class FakeSuccessApi(val responseDelay: Long) : MockApiService {

    override suspend fun getPokemonInfo(): PokemonInfo {
        return PokemonInfo(
            "pokemon",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        )
    }
}