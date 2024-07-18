package com.anna.usecase_coroutine_and_test.data.repo

import com.anna.usecase_coroutine_and_test.data.network.models.PokemonInfoResponse
import com.anna.usecase_coroutine_and_test.data.network.models.PokemonListResponse

//提高程式碼的可測試性和彈性
interface PokemonRepository {
    suspend fun fetchPokemonList(page: Int): Result<PokemonListResponse>
    suspend fun fetchPokemonInfo(name: String): Result<PokemonInfoResponse>
}