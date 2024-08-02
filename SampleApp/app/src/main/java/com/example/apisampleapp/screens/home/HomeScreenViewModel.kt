package com.example.apisampleapp.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(

) : ViewModel() {

    fun onPokemonCardClick() {
        Log.d("[debug]", "Pokemon card clicked...")
    }
}