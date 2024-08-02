package com.example.apisampleapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apisampleapp.routes.NavigationRoute

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = viewModel()
) {

    Column {
        Text("Home Screen")
        Button(
            onClick = {
                viewModel.onPokemonCardClick()
                navController.navigate(NavigationRoute.DETAILS.path)
            }
        ) {
            Text("Navigation Button")
        }
    }
}