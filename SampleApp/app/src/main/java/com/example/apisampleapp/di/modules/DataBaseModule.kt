package com.example.apisampleapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.apisampleapp.dao.PokemonDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module for providing Room DB related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    private companion object {
        const val POKEMON_DB = "pokemon-db"
    }

    @Provides
    @Singleton
    fun providesPokemonDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context = context,
            klass = PokemonDB::class.java,
            name = POKEMON_DB
        )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun providesPokemonDao(pokemonDB: PokemonDB) =
        pokemonDB.pokemonDao()
}