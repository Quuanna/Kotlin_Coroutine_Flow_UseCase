package com.example.usecase_coroutine_and_test.useCase5

import com.anna.usecase_coroutine_and_test.core.model.response.PokemonInfoResponse
import com.anna.usecase_coroutine_and_test.data.PokemonInfo
import com.anna.usecase_coroutine_and_test.mock.MockApiService
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
}