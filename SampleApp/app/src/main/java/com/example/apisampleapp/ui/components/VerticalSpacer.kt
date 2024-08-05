package com.example.apisampleapp.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * Predefined spacer for vertical scenarios.
 */
@Composable
fun VerticalSpacer(spaceSize: Dp) {
    Spacer(modifier = Modifier.height(spaceSize))
}