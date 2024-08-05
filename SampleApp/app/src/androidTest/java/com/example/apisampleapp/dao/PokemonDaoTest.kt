package com.example.apisampleapp.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.apisampleapp.dao.entities.PokemonEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PokemonDaoTest {

    // -------------------------------------------------------------
    // Tests set up
    // -------------------------------------------------------------

    private lateinit var database: PokemonDB
    private lateinit var pokemonDao: PokemonDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context = context,
            klass = PokemonDB::class.java
        ).build()

        pokemonDao = database.pokemonDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    // -------------------------------------------------------------
    // Tests
    // -------------------------------------------------------------

    @Test
    fun findById_returnsCorrectDbEntry() = runTest {
        // Given
        val pokemonId = 1
        val pokemon = createPokemonEntity(pokemonId)
        pokemonDao.insertAll(pokemon)

        // When
        val result = pokemonDao.findById(pokemonId)

        // Then
        assertEquals(pokemon, result)
    }

    @Test
    fun entryExists_returnsTrueWhenDbEntryExists() = runTest {
        // Given
        val pokemonId = 1
        val pokemon = createPokemonEntity(pokemonId)
        pokemonDao.insertAll(pokemon)

        // When
        val result = pokemonDao.entryExists(pokemonId)

        // Then
        assertTrue(result)
    }

    @Test
    fun entryExists_returnsFalseWhenDbEntryDoesNotExist() = runTest {
        // Given
        val pokemonId = 1

        // When
        val result = pokemonDao.entryExists(pokemonId)

        // Then
        assertFalse(result)
    }

    // -------------------------------------------------------------
    // Helper functions
    // -------------------------------------------------------------

    private fun createPokemonEntity(id: Int): PokemonEntity =
        PokemonEntity(
            id = id,
            displayId = "#001",
            name = "name",
            spriteUrl = "sample_url",
            type1 = 0,
            type2 = 1,
            baseHp = 10,
            baseAttack = 10,
            baseDefense = 10,
            baseSpAttack = 10,
            baseSpDefense = 10,
            baseSpeed = 10
        )
}