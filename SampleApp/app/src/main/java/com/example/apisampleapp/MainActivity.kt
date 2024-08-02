package com.example.apisampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apisampleapp.routes.NavigationRoute
import com.example.apisampleapp.screens.details.DetailsScreen
import com.example.apisampleapp.screens.home.HomeScreen
import com.example.apisampleapp.ui.theme.ApiSampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiSampleAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    SampleApiApp(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SampleApiApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationRoute.HOME.path
    ) {
        composable(route = NavigationRoute.HOME.path) {
            HomeScreen(navController)
        }
        composable(route = NavigationRoute.DETAILS.path) {
            DetailsScreen()
        }
    }
}