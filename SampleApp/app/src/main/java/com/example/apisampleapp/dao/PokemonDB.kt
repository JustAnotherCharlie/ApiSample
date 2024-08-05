package com.example.apisampleapp.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apisampleapp.dao.entities.PokemonEntity

/**
 * DataBase representation for implementing the [PokemonDao].
 */
@Database(entities = [PokemonEntity::class], version = 2)
abstract class PokemonDB : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}