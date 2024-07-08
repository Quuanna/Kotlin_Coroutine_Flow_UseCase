package com.example.usecase_coroutine_and_test.useCase3

import com.example.usecase_coroutine_and_test.core.api.PokemonService
import com.example.usecase_coroutine_and_test.core.model.response.PokemonInfoResponse
import com.example.usecase_coroutine_and_test.core.model.response.PokemonListResponse
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class FakeErrorApi(private val responseDelay: Long) : PokemonService {
    override suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResponse {
        delay(responseDelay)
        throw HttpException(
            Response.error<PokemonListResponse>(
                500,
                ResponseBody.create("application/json".toMediaTypeOrNull(), "")
            )
        )
    }

    override suspend fun getPokemonInfo(name: String): PokemonInfoResponse {
        throw HttpException(
            Response.error<PokemonInfoResponse>(
                500,
                ResponseBody.create("application/json".toMediaTypeOrNull(), "")
            )
        )
    }
}