package com.example.apisampleapp.network.models

import com.squareup.moshi.Json

/**
 * Data classes for mapping Pokemon entry details response
 */
data class PokemonEntry(
    val id: Int,
    val name: String,
    val sprites: PokemonEntrySprites,
    val types: List<PokemonEntryType>,
    val stats: List<PokemonEntryStat>
)

data class PokemonEntrySprites(
    @Json(name = "other") val others: PokemonEntrySpritesOthers
)

data class PokemonEntrySpritesOthers(
    @Json(name = "official-artwork") val official: PokemonEntrySpritesOfficial
)

data class PokemonEntrySpritesOfficial(
   @Json(name = "front_default") val url: String
)

data class PokemonEntryType(
    val slot: Int,
    val type: PokemonEntryTypeDetails
)

data class PokemonEntryTypeDetails(
    val name: String
)

data class PokemonEntryStat(
    @Json(name = "base_stat") val baseStat: Int,
    val stat: PokemonEntryStatDetails
)

data class PokemonEntryStatDetails(
    val name: String
)