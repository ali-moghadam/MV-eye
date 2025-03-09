package com.ar.mveye.contract

import androidx.compose.ui.graphics.Color
import com.ar.mv_eye.contract.UiState
import com.ar.mveye.product.model.Product

data class ProductUiState(
    val loading: Boolean = false,
    val product: Product? = null,
    val count: Int = 0,
    val selectedProductColor: Color? = null,
) : UiState