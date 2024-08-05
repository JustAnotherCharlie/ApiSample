package com.example.apisampleapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.apisampleapp.usecase.UIPokemonEntry
import com.example.apisampleapp.util.PokemonType
import com.example.apisampleapp.util.dimen.AppDimens
import com.example.apisampleapp.util.dimen.AppFontSizes
import com.example.apisampleapp.util.getAlphaColor


/**
 * Composable UI component for displaying basic information card
 * respecting a specific Pokemon.
 *
 * - [entry] - Specific pokemon data to display.
 * - [onClick] - Clickable action by the card.
 */
@Composable
fun PokemonEntryCard(
    entry: UIPokemonEntry,
    onClick: (id: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = AppDimens.space_sm),
        colors = CardColors(
            contentColor = Color.White,
            containerColor = entry.types.first().getAlphaColor(),
            disabledContentColor = Color.White,
            disabledContainerColor = Color.White
        ),
        onClick = { onClick(entry.id) }
    ) {
        Column(
            modifier = Modifier.padding(
                vertical = AppDimens.space_sm,
                horizontal = AppDimens.space_md
            ),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = entry.displayId,
                fontSize = AppFontSizes.font_md,
                fontWeight = FontWeight.Bold
            )
            Row {
                Column(
                    modifier = Modifier
                        .weight(PokemonEntryValues.CONTENT_WEIGHT)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = entry.name,
                        fontSize = AppFontSizes.font_md,
                        fontWeight = FontWeight.Bold
                    )
                    VerticalSpacer(AppDimens.space_sm)
                    PokemonTypeBadge(
                        pokemonType = entry.types.first()
                    )
                    if (entry.types.size > 1) {
                        VerticalSpacer(AppDimens.space_sm)
                        PokemonTypeBadge(
                            pokemonType = entry.types.last()
                        )
                    }
                }
                AsyncImage(
                    modifier = Modifier
                        .weight(PokemonEntryValues.CONTENT_WEIGHT)
                        .size(PokemonEntryValues.pkImgSize),
                    model = entry.spriteUrl,
                    contentDescription = null
                )
            }

        }
    }
}

private object PokemonEntryValues{
    const val CONTENT_WEIGHT = 0.5f
    val pkImgSize = 120.dp
}

@Composable
@Preview(heightDp = 150)
private fun PreviewPokemonEntryCard() {
    val samplePokemon = UIPokemonEntry(
        id = 1,
        displayId = "#001",
        name = "Bulbasaur",
        spriteUrl = "",
        types = listOf(PokemonType.GRASS, PokemonType.POISON)
    )
    PokemonEntryCard(samplePokemon) { }
}