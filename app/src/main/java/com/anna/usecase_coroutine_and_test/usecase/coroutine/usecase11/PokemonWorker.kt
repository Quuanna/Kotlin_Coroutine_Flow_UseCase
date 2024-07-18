package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase11

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import com.anna.usecase_coroutine_and_test.mock.api.MockNetworkInterceptor
import com.anna.usecase_coroutine_and_test.mock.api.createMockApi
import com.google.gson.Gson

class PokemonWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    private val apiService = mockApi()

    companion object {
        fun mockApi() = createMockApi(
            interceptor = MockNetworkInterceptor().mock(
                "http://localhost/pokemon",
                {
                    Gson().toJson(
                        PokemonInfo(
                            name = "pokemon",
                            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
                        )
                    )
                },
                200,
                1000,
                persist = false
            )
        )
    }

    override suspend fun doWork(): Result {
        return try {
            Log.d("TEST", Thread.currentThread().name)
            apiService.getPokemonInfo()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}