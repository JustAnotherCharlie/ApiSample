package com.example.apisampleapp.network.models

/**
 * API Pokemon List data representation.
 */
data class PokemonList(
    val results: List<PokemonResult>
)

/**
 * API pokemon item representation.
 */
data class PokemonResult(
    val name: String,
    val url: String
)