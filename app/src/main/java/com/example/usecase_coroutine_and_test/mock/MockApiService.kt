package com.example.usecase_coroutine_and_test.mock

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
