package com.example.usecase_coroutine_and_test.useCase5

import com.anna.usecase_coroutine_and_test.data.PokemonInfo
import com.anna.usecase_coroutine_and_test.mock.MockApiService
import kotlinx.coroutines.delay
import java.io.IOException

class FakeSuccessOnThirdAttemptApi(val responseDelay: Long): MockApiService {

    var requestCount = 0

    override suspend fun getPokemonInfo(): PokemonInfo {
        requestCount++
        delay(responseDelay)
        if (requestCount < 3) {
            throw IOException()
        }
        return PokemonInfo(
            "pokemon",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        )
    }
}