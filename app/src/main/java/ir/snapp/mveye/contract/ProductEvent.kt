package ir.snapp.mveye.contract

import androidx.compose.ui.graphics.Color

sealed class ProductEvent : MvEvent {
    object IncreaseCount : ProductEvent()
    object DecreaseCount : ProductEvent()
    data class SelectColor(val color: Color) : ProductEvent()
}
