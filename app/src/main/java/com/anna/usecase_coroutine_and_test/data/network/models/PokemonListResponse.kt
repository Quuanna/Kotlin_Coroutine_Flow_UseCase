package com.anna.usecase_coroutine_and_test.data.network.models



data class PokemonListResponse(
    val count: Int, // 1302
    val next: String,
    val previous: Any?, // null
    val results: List<Result>
) {
    data class Result(
        val name: String, // bulbasaur
        val url: String // https://pokeapi.co/api/v2/pokemon/1/
    )
}