package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase5


import androidx.lifecycle.viewModelScope
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.data.PokemonInfo
import com.example.usecase_coroutine_and_test.usecase.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineUseCase5ViewModel(val api: MockApiService = mockApi()) : BaseViewModel<PokemonInfo>() {

    fun performNetworkRequest() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            val numberRetries = 2
            try {
                retry(times= numberRetries) {
                    val pokemon = api.getPokemonInfo()
                    pokemonInfo.value = PokemonInfo(name = pokemon.name, imageUrl = pokemon.imageUrl)
                    uiState.value = UiState.Success
                }

            } catch (e: Exception) {
                uiState.value = UiState.Error("Network Request failed")
            }
        }
    }


    private suspend fun <T> retry(
        times: Int,
        initialDelayMillis:Long = 100, // 初始延遲時間
        maxDelayMillis: Long = 1000,
        factor:Double = 2.0, // retry with exponential backoff
        block: suspend () -> T
    ): T {
        var currentDelay = initialDelayMillis
        repeat(times) {
            try {
                return block()
            } catch (_: Exception) {
            }
            delay(currentDelay)
            currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelayMillis)
        }

        return block() // last attempt
    }
}