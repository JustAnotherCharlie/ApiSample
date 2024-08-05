package com.example.apisampleapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.apisampleapp.util.PokemonStat
import com.example.apisampleapp.util.PokemonStatValue

/**
 * Composable UI component for displaying pokemon base stat values
 * within a line chart.
 *
 * [PokemonStat] - Specific pokemon stat to display.
 */
@Composable
fun BaseStatChart(pokemonStat: PokemonStat) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(0.20f),
            text = stringResource(pokemonStat.displayLabel)
        )

        Text(
            modifier = Modifier.weight(0.1f),
            text = pokemonStat.statValue.toString()
        )
        LinearProgressIndicator(
            modifier = Modifier.weight(0.7f),
            progress = { pokemonStat.statValue.div(
                when (pokemonStat) {
                    PokemonStat.HEALTH_POINTS -> PokemonStatValue.HP_STAT_TOP_BASE_VALUE
                    else -> PokemonStatValue.OTHER_STAT_TOP_BASE_VALUE
                }
            ) },
            color = pokemonStat.statColor,
            trackColor = Color.LightGray.copy(alpha = 0.5f)
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewHpBaseStatCharts() {
    val hpStat = PokemonStat.HEALTH_POINTS
    hpStat.statValue = 100
    BaseStatChart(pokemonStat = hpStat)
}

@Composable
@Preview(showBackground = true)
private fun PreviewOtherBaseStatCharts() {
    val attackStat = PokemonStat.ATTACK
    attackStat.statValue = 100
    BaseStatChart(pokemonStat = attackStat)
}