package com.example.apisampleapp.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.apisampleapp.R
import com.example.apisampleapp.ui.components.AppTitle
import com.example.apisampleapp.ui.components.BaseStatChart
import com.example.apisampleapp.ui.components.HorizontalSpacer
import com.example.apisampleapp.ui.components.PokemonTypeBadge
import com.example.apisampleapp.ui.components.VerticalSpacer
import com.example.apisampleapp.usecase.UIPokemonEntry
import com.example.apisampleapp.util.PokemonType
import com.example.apisampleapp.util.dimen.AppDimens
import com.example.apisampleapp.util.dimen.AppFontSizes
import com.example.apisampleapp.util.getAlphaColor

/**
 * Presentable screen displaying the details of a specific pokemon.
 *
 * - [navController] - Stack [NavController] reference.
 * - [pokemonId] - Id referring to the specific pokemon to load.
 * - [viewModel] - ViewModel reference of the [DetailsScreenViewModel].
 *
 */
@Composable
fun DetailsScreen(
    navController: NavController,
    pokemonId: Int,
    viewModel: DetailsScreenViewModel = viewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.getPokemonDetails(pokemonId)
    }

    val pokemonDetailsState = viewModel.pokemonDetailsStateFlow.collectAsState()

    DetailsScreenLayout(
        pokemonDetails = pokemonDetailsState.value,
        onBackClick = { navController.navigateUp() }
    )
}

@Composable
private fun DetailsScreenLayout(
    pokemonDetails: UIPokemonEntry?,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        pokemonDetails?.let {
            Card(
                colors = CardDefaults.cardColors().copy(
                    containerColor = pokemonDetails.types.first().getAlphaColor(),
                    contentColor = Color.White
                ),
                shape = RectangleShape
            ) {
                Column(
                    modifier = Modifier
                        .padding(AppDimens.space_md)
                ) {
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack,
                        modifier = Modifier.size(30.dp)
                            .clickable(onClick = onBackClick),
                        contentDescription = null,
                        tint = Color.White
                    )
                    VerticalSpacer(AppDimens.space_md)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AppTitle(pokemonDetails.name)
                        AppTitle(pokemonDetails.displayId)
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = AppDimens.space_sm)
                    ) {
                        PokemonTypeBadge(pokemonType = pokemonDetails.types.first())

                        if (pokemonDetails.types.size > 1) {
                            HorizontalSpacer(spaceSize = AppDimens.space_sm)
                            PokemonTypeBadge(pokemonType = pokemonDetails.types.last())
                        }
                    }
                    AsyncImage(
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth(),
                        model = pokemonDetails.spriteUrl,
                        contentDescription = null
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                topStart = AppDimens.space_md,
                                topEnd = AppDimens.space_md
                            )
                        )
                        .fillMaxWidth()
                        .height(AppDimens.space_lg)
                        .background(Color.White)
                )
            }
            Column(
                modifier = Modifier
                    .padding(AppDimens.space_md)
            ) {
                Text(
                    text = stringResource(R.string.details_base_stats),
                    fontSize = AppFontSizes.font_md,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                VerticalSpacer(spaceSize = AppDimens.space_lg)
                BaseStatChart(pokemonStat = pokemonDetails.stats.hpStat)
                VerticalSpacer(spaceSize = AppDimens.space_sm)
                BaseStatChart(pokemonStat = pokemonDetails.stats.attackStat)
                VerticalSpacer(spaceSize = AppDimens.space_sm)
                BaseStatChart(pokemonStat = pokemonDetails.stats.defenseStat)
                VerticalSpacer(spaceSize = AppDimens.space_sm)
                BaseStatChart(pokemonStat = pokemonDetails.stats.spAttackStat)
                VerticalSpacer(spaceSize = AppDimens.space_sm)
                BaseStatChart(pokemonStat = pokemonDetails.stats.spDefenseStat)
                VerticalSpacer(spaceSize = AppDimens.space_sm)
                BaseStatChart(pokemonStat = pokemonDetails.stats.speedStat)
            }
        }
    }
}

@Composable
@Preview
private fun PreviewDetailsLayout() {
    val detailsSample = UIPokemonEntry(
        id = 1,
        displayId = "#001",
        name = "Bulbasaur",
        spriteUrl = "",
        types = listOf(PokemonType.GRASS, PokemonType.POISON)
    )
    DetailsScreenLayout(detailsSample) { }
}

