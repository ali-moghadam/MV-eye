package com.alirnp.mveye

import ali.rnp.mveye.R
import androidx.compose.ui.graphics.Color
import com.alirnp.mveye.product.model.Product

object Utils {

    val product: Product = Product(
        id = 1,
        name = "Beats Studio Pro",
        price = 349.99,
        colors = listOf(
            Color.Black,
            Color.Blue,
            Color.Red,
        ),
        image = R.drawable.headphone
    )
}