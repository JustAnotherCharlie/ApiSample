package com.example.apisampleapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apisampleapp.dao.entities.PokemonEntity

/**
 * DAO for pokemon entity data.
 *
 * @see [PokemonEntity]
 */
@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemonentity WHERE id LIKE :pokemonId")
    fun findById(pokemonId: Int): PokemonEntity

    @Query("SELECT EXISTS(SELECT * FROM pokemonentity WHERE id = :id)")
    fun entryExists(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pokemonEntity: PokemonEntity)
}