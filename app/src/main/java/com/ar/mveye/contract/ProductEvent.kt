package com.ar.mveye.contract

import androidx.compose.ui.graphics.Color
import com.ar.mv_eye.contract.Event

sealed class ProductEvent : Event {
    object IncreaseCount : ProductEvent()
    object DecreaseCount : ProductEvent()
    data class SelectColor(val color: Color) : ProductEvent()
}
