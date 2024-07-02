package com.example.usecase_coroutine_and_test.repo

import com.example.usecase_coroutine_and_test.core.model.response.PokemonInfoResponse
import com.example.usecase_coroutine_and_test.core.model.response.PokemonListResponse

//提高程式碼的可測試性和彈性
interface PokemonRepository {
    suspend fun fetchPokemonList(page: Int): Result<PokemonListResponse>
    suspend fun fetchPokemonInfo(name: String): Result<PokemonInfoResponse>
}