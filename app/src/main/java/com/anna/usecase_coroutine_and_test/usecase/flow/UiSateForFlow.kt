package com.anna.usecase_coroutine_and_test.usecase.flow


sealed class UiSateForFlow {
    data object Loading : UiSateForFlow()
    data class Success(val pokemonInfo: List<PokemonList>) : UiSateForFlow()
    data class Error(val message: String) : UiSateForFlow()
}


data class PokemonList(
    val id: Int, val name: String
)