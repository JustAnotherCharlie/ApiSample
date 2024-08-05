package com.example.apisampleapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apisampleapp.util.dimen.AppDimens
import com.valentinilk.shimmer.shimmer

/**
 * Composable for presenting a shimmering card box of fix height.
 */
@Composable
fun LoadingCard() {
    Box(
        modifier = Modifier
            .padding(vertical = AppDimens.space_sm)
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray)
            .shimmer()
    )
}

@Composable
@Preview
private fun PreviewLoadingCard() {
    LoadingCard()
}