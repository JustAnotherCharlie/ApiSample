package com.example.apisampleapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apisampleapp.util.PokemonType

/**
 * Composable UI component for displaying pokemon typing badge
 * with the respective icon and color representation.
 *
 * - [pokemonType] - Specific pokemon type to fill the typing badge.
 * - Each pokemon type have a unique icon and color associated to it.
 *
 * @see PokemonType
 */
@Composable
fun PokemonTypeBadge(pokemonType: PokemonType) {
    Row(
        modifier = Modifier
            .height(25.dp)
            .width(90.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                .fillMaxHeight()
                .width(20.dp)
                .background(pokemonType.color),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxHeight(),
                painter = painterResource(id = pokemonType.typeIcon),
                contentDescription = null
            )
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = pokemonType.name.lowercase(),
                color = Color.White
            )
        }
    }
}

@Composable
@Preview(heightDp = 100)
fun PreviewPokemonTypeBadge() {
    Column(
        verticalArrangement = Arrangement.SpaceAround
    ) {
        PokemonTypeBadge(pokemonType = PokemonType.GRASS)
        PokemonTypeBadge(pokemonType = PokemonType.FIRE)
        PokemonTypeBadge(pokemonType = PokemonType.DRAGON)
    }
}