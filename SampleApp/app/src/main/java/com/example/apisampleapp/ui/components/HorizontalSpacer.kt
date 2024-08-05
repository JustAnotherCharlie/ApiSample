package com.example.apisampleapp.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * Predefined spacer for horizontal scenarios.
 */
@Composable
fun HorizontalSpacer(spaceSize: Dp) {
    Spacer(modifier = Modifier.width(spaceSize))
}