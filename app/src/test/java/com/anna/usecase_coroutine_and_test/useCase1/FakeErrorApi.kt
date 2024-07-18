package com.anna.usecase_coroutine_and_test.useCase1

import com.anna.usecase_coroutine_and_test.EndpointShouldNotBeCalledException
import com.anna.usecase_coroutine_and_test.core.api.PokemonService
import com.anna.usecase_coroutine_and_test.data.network.models.PokemonInfoResponse
import com.anna.usecase_coroutine_and_test.data.network.models.PokemonListResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class FakeErrorApi : PokemonService {
    override suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResponse {
        throw HttpException(
            Response.error<PokemonListResponse>(
                500,
                ResponseBody.create("application/json".toMediaTypeOrNull(), "")
            )
        )
    }

    override suspend fun getPokemonInfo(name: String): PokemonInfoResponse {
        // 此案例不用
        throw EndpointShouldNotBeCalledException()
    }
}