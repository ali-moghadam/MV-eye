package com.ar.mveye.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.mveye.utils.ProductGenerator

@Composable
internal fun ProductColorComponent(
    colors: List<Color>,
    selectedColor: Color,
    onColorSelect: (Color) -> Unit,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        items(colors) { color ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(32.dp)
                    .clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ) { onColorSelect(color) }
                    .background(color = color, shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                if (selectedColor == color) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProductColorComponentPreview() {
    val product = ProductGenerator.sampleProduct()

    ProductColorComponent(
        colors = product.colors,
        selectedColor = product.colors.first(),
        onColorSelect = { }
    )
}