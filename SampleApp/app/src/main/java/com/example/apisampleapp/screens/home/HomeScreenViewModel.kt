package com.example.apisampleapp.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apisampleapp.network.PokemonApiRepository
import com.example.apisampleapp.network.models.PokemonEntry
import com.example.apisampleapp.usecase.GetPokemonEntryDetailsUseCase
import com.example.apisampleapp.usecase.UIPokemonEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPokemonEntryDetailsUseCase: GetPokemonEntryDetailsUseCase,
    private val repository: PokemonApiRepository
) : ViewModel() {

    private companion object {
        const val POKEMON_ENTRIES = 151
    }

    private val _pokemonEntryListStateFlow = MutableStateFlow(getDefaultEmptyList())
    val pokemonEntryListStateFlow: StateFlow<List<UIPokemonEntry?>>
        get() = _pokemonEntryListStateFlow

    private val _loadingItemsStateFlow = MutableStateFlow(true)
    val loadingItemsStateFlow: StateFlow<Boolean>
        get() = _loadingItemsStateFlow

    init {
        viewModelScope.launch { fetchPokemonList() }
    }

    private suspend fun fetchPokemonList() {
        withContext(Dispatchers.IO) {
            val pokemonList = repository.getPokemonList(POKEMON_ENTRIES)
            val pokemonEntries = List(pokemonList.results.size) { index ->
                val entryDetails = getPokemonEntryDetailsUseCase.execute(index + 1)
                return@List entryDetails
            }
            withContext(Dispatchers.Main) {
                _pokemonEntryListStateFlow.update { pokemonEntries }
                _loadingItemsStateFlow.update { false }
            }
        }
    }

    fun onPokemonCardClick() {
        Log.d("[debug]", "Pokemon card clicked...")
    }

    private fun getDefaultEmptyList(): List<UIPokemonEntry?> =
        emptyList()
}

//data class PokemonEntryDetails(
//    val id: Int,
//    val name: String,
//    val spriteUrl: String,
//    val type1: PokemonType,
//    val type2: PokemonType? = null
//)