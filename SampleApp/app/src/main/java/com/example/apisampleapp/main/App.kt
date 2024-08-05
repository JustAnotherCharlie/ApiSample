package com.example.apisampleapp.main

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apisampleapp.routes.NavigationRoute
import com.example.apisampleapp.screens.details.DetailsScreen
import com.example.apisampleapp.screens.details.DetailsScreenViewModel
import com.example.apisampleapp.screens.home.HomeScreen
import com.example.apisampleapp.screens.home.HomeScreenViewModel

/**
 * App composable base set up.
 */
@Composable
fun App(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationRoute.HOME.path
    ) {
        composable(route = NavigationRoute.HOME.path) {
            val viewModel = hiltViewModel<HomeScreenViewModel>()
            HomeScreen(navController, viewModel)
        }
        composable(
            route = "${NavigationRoute.DETAILS.path}/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ) { backStackEntry ->
            val viewModel = hiltViewModel<DetailsScreenViewModel>()
            DetailsScreen(
                navController = navController,
                pokemonId =  backStackEntry.arguments?.getInt("id") ?: 0,
                viewModel = viewModel
            )
        }
    }
}