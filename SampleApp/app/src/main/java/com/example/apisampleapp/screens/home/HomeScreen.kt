package com.example.apisampleapp.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apisampleapp.R
import com.example.apisampleapp.routes.NavigationRoute
import com.example.apisampleapp.ui.components.AppTitle
import com.example.apisampleapp.ui.components.LoadingCard
import com.example.apisampleapp.ui.components.PokemonEntryCard
import com.example.apisampleapp.ui.components.VerticalSpacer
import com.example.apisampleapp.usecase.UIPokemonEntry
import com.example.apisampleapp.util.PokemonType
import com.example.apisampleapp.util.dimen.AppDimens

/**
 * Presentable screen that displays a list of pokemon.
 * - [navController] - Stack [NavController] reference.
 * - [viewModel] - ViewModel reference of the [HomeScreenViewModel].
 */
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = viewModel()
) {
    val pokemonList = viewModel.pokemonEntryListStateFlow.collectAsState()
    val loadingItems = viewModel.loadingItemsStateFlow.collectAsState()

    HomeScreenLayout(
        pokemonList = pokemonList.value,
        pokemonCardOnClick = {
            navController.navigate("${NavigationRoute.DETAILS.path}/${it}")
        },
        loadingItems = loadingItems.value
    )
}

@Composable
private fun HomeScreenLayout(
    pokemonList: List<UIPokemonEntry?> = emptyList(),
    pokemonCardOnClick: (id: Int) -> Unit,
    loadingItems: Boolean
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppDimens.space_md)
    ) {
        item { AppTitle(stringResource(R.string.home_title)) }
        item { VerticalSpacer(AppDimens.space_md)}

        if (!loadingItems) {
            pokemonList.forEach { entry ->
                entry?.let {
                    item {
                        PokemonEntryCard(it, pokemonCardOnClick)
                    }
                }
            }
        } else {
            item { LoadingCard() }
            item { LoadingCard() }
            item { LoadingCard() }
            item { LoadingCard() }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewLoadingHomeScreenLayout() {
    HomeScreenLayout(
        pokemonList = emptyList(),
        pokemonCardOnClick = {},
        loadingItems = true
    )
}

@Composable
@Preview(showBackground = true)
private fun PreviewLoadedHomeScreenLayout() {
    val samplePokemon = UIPokemonEntry(
        id = 1,
        displayId = "#001",
        name = "Bulbasaur",
        spriteUrl = "",
        types = listOf(PokemonType.GRASS, PokemonType.POISON)
    )

    HomeScreenLayout(
        pokemonList = listOf(samplePokemon, samplePokemon, samplePokemon),
        pokemonCardOnClick = {},
        loadingItems = false
    )
}
