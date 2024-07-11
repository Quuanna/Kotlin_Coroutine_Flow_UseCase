package com.anna.usecase_coroutine_and_test.useCase5

import com.anna.usecase_coroutine_and_test.data.network.models.PokemonInfoResponse
import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import com.anna.usecase_coroutine_and_test.mock.api.MockApiService
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class FakeErrorApi(private val responseDelay: Long) : MockApiService {

    var requestCount = 0
    override suspend fun getPokemonInfo(): PokemonInfo {
        requestCount++
        delay(responseDelay)
        throw HttpException(
            Response.error<PokemonInfoResponse>(
                500,
                ResponseBody.create("application/json".toMediaTypeOrNull(), "")
            )
        )
    }

    override suspend fun getPokemonInfoCode(code: Int): PokemonInfo {
        throw HttpException(
            Response.error<PokemonInfoResponse>(
                500,
                ResponseBody.create("application/json".toMediaTypeOrNull(), "")
            )
        )
    }
}