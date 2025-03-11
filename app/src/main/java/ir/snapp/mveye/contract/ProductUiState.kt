package ir.snapp.mveye.contract

import androidx.compose.ui.graphics.Color
import ir.snapp.mveye.model.Product

data class ProductUiState(
    val loading: Boolean = false,
    val product: Product? = null,
    val count: Int = 0,
    val selectedProductColor: Color? = null,
) : MvUiState