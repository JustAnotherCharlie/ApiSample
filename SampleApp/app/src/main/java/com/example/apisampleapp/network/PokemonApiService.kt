package com.example.apisampleapp.network

import com.example.apisampleapp.network.models.PokemonEntry
import com.example.apisampleapp.network.models.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface defining the specific retrofit methods for the connection towards
 * the pokemon API.
 */
interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int = 0
    ): Response<PokemonList>

    @GET("pokemon/{id}")
    suspend fun getPokemonEntryDetails(
        @Path("id") pokemonId: Int
    ): Response<PokemonEntry>
}