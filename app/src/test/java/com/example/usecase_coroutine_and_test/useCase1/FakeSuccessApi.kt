package com.example.usecase_coroutine_and_test.useCase1

import com.example.usecase_coroutine_and_test.EndpointShouldNotBeCalledException
import com.example.usecase_coroutine_and_test.core.api.PokemonService
import com.example.usecase_coroutine_and_test.core.model.response.PokemonInfoResponse
import com.example.usecase_coroutine_and_test.core.model.response.PokemonListResponse

class FakeSuccessApi : PokemonService {

    override suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResponse {
        return PokemonListResponse(
            count = 1302,
            next = "https://pokeapi.co/api/v2/pokemon/?offset=25&limit=25",
            previous = null,
            results = listOf(
                PokemonListResponse.Result(
                    "bulbasaur",
                    " https://pokeapi.co/api/v2/pokemon/1/"
                )
            )
        )
    }

    override suspend fun getPokemonInfo(name: String): PokemonInfoResponse {
        // 此案例不用
        throw EndpointShouldNotBeCalledException()
    }
}