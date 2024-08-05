package com.example.apisampleapp.dao.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity for reflecting pokemon details data.
 */
@Entity
data class PokemonEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "pokemon_display_id") val displayId: String,
    @ColumnInfo(name = "pokemon_name") val name: String,
    @ColumnInfo(name = "pokemon _sprite_url") val spriteUrl: String,
    @ColumnInfo(name = "pokemon_type_1") val type1: Int,
    @ColumnInfo(name = "pokemon_type_2") val type2: Int? = null,
    @ColumnInfo(name = "pokemon_base_hp") val baseHp: Int,
    @ColumnInfo(name = "pokemon_base_attack") val baseAttack: Int,
    @ColumnInfo(name = "pokemon_base_defense") val baseDefense: Int,
    @ColumnInfo(name = "pokemon_base_special_attack") val baseSpAttack: Int,
    @ColumnInfo(name = "pokemon_base_special_defense") val baseSpDefense: Int,
    @ColumnInfo(name = "pokemon_base_speed") val baseSpeed: Int,
)