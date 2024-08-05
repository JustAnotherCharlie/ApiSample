package com.example.apisampleapp.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apisampleapp.usecase.GetPokemonEntryDetailsUseCase
import com.example.apisampleapp.usecase.UIPokemonEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel for the DetailsScreen.
 */
@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getPokemonEntryDetailsUseCase: GetPokemonEntryDetailsUseCase
) : ViewModel() {

    private val _pokemonDetailsStateFlow = MutableStateFlow<UIPokemonEntry?>(null)
    val pokemonDetailsStateFlow: StateFlow<UIPokemonEntry?>
        get() = _pokemonDetailsStateFlow

    fun getPokemonDetails(pokemonId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getPokemonEntryDetails(pokemonId)
            }
        }
    }

    private suspend fun getPokemonEntryDetails(pokemonId: Int) {
        val pokemonEntry = getPokemonEntryDetailsUseCase.execute(pokemonId)
        withContext(Dispatchers.Main) {
            _pokemonDetailsStateFlow.update { pokemonEntry }
        }
    }
}