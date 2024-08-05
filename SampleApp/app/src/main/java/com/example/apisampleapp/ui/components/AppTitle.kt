package com.example.apisampleapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.apisampleapp.util.dimen.AppFontSizes

/**
 * Predefined Text composable with large fontSize and Bold fontWeight.
 */
@Composable
fun AppTitle(text: String) {
    Text(
        text,
        fontSize = AppFontSizes.font_lg,
        fontWeight = FontWeight.Bold
    )
}

@Composable
@Preview(showBackground = true)
private fun PreviewAppTitle() {
    Text(text = "Testing title")
}