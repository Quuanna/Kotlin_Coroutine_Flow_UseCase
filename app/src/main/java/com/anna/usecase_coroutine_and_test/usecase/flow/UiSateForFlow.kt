package com.anna.usecase_coroutine_and_test.usecase.flow

import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo

sealed class UiSateForFlow {
    data object Loading: UiSateForFlow()
    data class Success(val pokemonInfo: PokemonInfo): UiSateForFlow()
    data class Error(val message: String): UiSateForFlow()
}
