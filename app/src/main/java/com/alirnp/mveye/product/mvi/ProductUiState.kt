package com.alirnp.mveye.product.mvi

import androidx.compose.ui.graphics.Color
import com.alirnp.mv_eye.contract.UiState
import com.alirnp.mveye.product.model.Product

data class ProductUiState(
    val loading: Boolean = false,
    val product: Product? = null,
    val count: Int = 0,
    val selectedProductColor: Color? = null,
) : UiState