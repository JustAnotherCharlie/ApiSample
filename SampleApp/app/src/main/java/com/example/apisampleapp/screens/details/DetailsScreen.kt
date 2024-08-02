package com.example.apisampleapp.screens.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel = viewModel()
) {
    Text(text = "Details Screen")
}