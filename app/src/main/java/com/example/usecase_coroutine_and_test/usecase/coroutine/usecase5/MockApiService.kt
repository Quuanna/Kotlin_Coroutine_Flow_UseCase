package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase5

import com.example.usecase_coroutine_and_test.data.PokemonInfo
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MockApiService {

    @GET("pokemon")
    suspend fun getPokemonInfo(): PokemonInfo
}

fun createMockApi(interceptor: MockNetworkInterceptor): MockApiService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(MockApiService::class.java)
}

fun mockApi() = createMockApi(
    MockNetworkInterceptor().mock(
        "http://localhost/pokemon",
        { "something went wrong on server side" },
        500,
        1000,
        persist = false
    ).mock(
        "http://localhost/pokemon",
        { "something went wrong on server side" },
        500,
        1000,
        persist = false
    ).mock(
        "http://localhost/pokemon",
        {
            Gson().toJson(
                PokemonInfo(
                    "pokemon",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
                )
            )
        },
        200,
        1000
    )
)