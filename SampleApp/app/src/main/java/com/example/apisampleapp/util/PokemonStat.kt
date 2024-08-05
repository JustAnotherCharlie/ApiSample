package com.example.apisampleapp.util

import androidx.compose.ui.graphics.Color
import com.example.apisampleapp.R

/**
 * Enum class for defining the possible base classification
 * for a pokemon stats.
 *
 * [identifier] - API base stat name.
 *
 * [displayLabel] - Visible name for the UI to display.
 *
 * [statColor] - Designated color for UI representation.
 *
 * [statValue] - Provided value by the API stat data.
 *
 * Note:
 * - For [HEALTH_POINTS]: the maximum possible base stat is 255.
 * - For [ATTACK], [DEFENSE], [SPECIAL_ATTACK], [SPECIAL_DEFENSE], and [SPEED]:
 *   the maximum possible base stat is 135.
 */
enum class PokemonStat(
    val identifier: String,
    val displayLabel: Int,
    val statColor: Color,
    var statValue: Int = 0
) {
    HEALTH_POINTS("hp", R.string.type_hp_display, Color(0xFF69DC12)),
    ATTACK("attack", R.string.type_attack_display, Color(0xFFEFCC18)),
    DEFENSE("defense",R.string.type_defense_display, Color(0xFFE86412)),
    SPECIAL_ATTACK("special-attack",R.string.type_special_attack_display, Color(0xFF14C3F1)),
    SPECIAL_DEFENSE("special-defense",R.string.type_special_defense_display, Color(0xFF4A6ADF)),
    SPEED("speed",R.string.type_speed_display, Color(0xFFD51DAD)),
    UNDEFINED("", R.string.type_undefined_display, Color.White)
}

/**
 * Default stat values for [PokemonStat]
 * - [HP_STAT_TOP_BASE_VALUE] - Use for Health Points stat.
 * - [OTHER_STAT_TOP_BASE_VALUE] - Use for any other stat besides Health Points.
 */
object PokemonStatValue {
    const val HP_STAT_TOP_BASE_VALUE = 255f
    const val OTHER_STAT_TOP_BASE_VALUE = 135f
}