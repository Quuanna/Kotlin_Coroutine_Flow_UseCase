package com.anna.usecase_coroutine_and_test.core.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

class PokemonCline {

    companion object {
        val apiService by lazy { createRetrofit() }

        private fun createRetrofit(): PokemonService {
            val loggingInterceptor = HttpLoggingInterceptor()
                .apply { level = HttpLoggingInterceptor.Level.BODY }

            val okhttpCline = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpCline)
                .build()

            return retrofit.create(PokemonService::class.java)
        }
    }

}