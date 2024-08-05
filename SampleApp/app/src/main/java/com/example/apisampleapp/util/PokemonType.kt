package com.example.apisampleapp.util

import androidx.compose.ui.graphics.Color
import com.example.apisampleapp.R

/**
 * Enum class containing all the possible pokemon types that
 * can be returned by the Pokemon public API.
 *
 * [color] - Official associated color with the respective pokemon type.
 */
enum class PokemonType(val color: Color, val typeIcon: Int) {
    NORMAL(Color(0xFF9FA19F), R.drawable.type_normal_icon),
    FIGHTING(Color(0xFFFF8000), R.drawable.type_fighting_icon),
    FLYING(Color(0xFF81B9EF), R.drawable.type_flying_icon),
    POISON(Color(0xFF9141CB), R.drawable.type_poison_icon),
    GROUND(Color(0xFF915121), R.drawable.type_ground_icon),
    ROCK(Color(0xFFAFA981), R.drawable.type_rock_icon),
    BUG(Color(0xFF91A119), R.drawable.type_bug_icon),
    GHOST(Color(0xFF704170), R.drawable.type_ghost_icon),
    STEEL(Color(0xFF60A1B8), R.drawable.type_steel_icon),
    FIRE(Color(0xFFE62829), R.drawable.type_fire_icon),
    WATER(Color(0xFF2980EF), R.drawable.type_water_icon),
    GRASS(Color(0xFF3FA129), R.drawable.type_grass_icon),
    ELECTRIC(Color(0xFFFAC000), R.drawable.type_electric_icon),
    PSYCHIC(Color(0xFFEF4179), R.drawable.type_psychic_icon),
    ICE(Color(0xFF3DCEF3), R.drawable.type_ice_icon),
    DRAGON(Color(0xFF5060E1), R.drawable.type_dragon_icon),
    DARK(Color(0xFF624D4E), R.drawable.type_dark_icon),
    FAIRY(Color(0xFFEF70EF), R.drawable.type_fairy_icon),
    STELLAR(Color(0xFF40B5A5), R.drawable.type_stellar_icon),
    UNKNOWN(Color(0xFF68A090), R.drawable.type_normal_icon)
}

/**
 * Retrieves respective pokemon type color with a 70% alpha.
 */
fun PokemonType.getAlphaColor() =
    color.copy(alpha = 0.7f)