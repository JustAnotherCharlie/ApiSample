package com.example.apisampleapp.network

import com.example.apisampleapp.network.models.PokemonEntry
import com.example.apisampleapp.network.models.PokemonList
import javax.inject.Inject

/**
 * Class defining the repository bridge for the retrofit api service.
 *
 * - [pokemonApiService] - retrofit service instance.
 */
class PokemonApiRepository @Inject constructor(
    private val pokemonApiService: PokemonApiService
) {
    suspend fun getPokemonList(entriesLimit: Int): PokemonList {
        val response = pokemonApiService.getPokemonList(entriesLimit)
        return response.body() ?: PokemonList(emptyList())
    }

    suspend fun getPokemonEntryDetails(pokemonId: Int): PokemonEntry? {
        val response = pokemonApiService.getPokemonEntryDetails(pokemonId)
        return response.body()
    }
}