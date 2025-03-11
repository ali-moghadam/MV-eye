package ir.snapp.mveye.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Product(
    val id: Long,
    val name: String,
    val price: Double,
    val colors: List<Color>,
    @DrawableRes val image: Int,
)