package com.alirnp.mveye.product

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alirnp.mv_eye.impl.StateProducerImpl
import com.alirnp.mveye.Utils
import com.alirnp.mveye.product.mvi.ProductEvent
import com.alirnp.mveye.product.mvi.ProductUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val productId: Long = 1

    private val stateProducer = StateProducerImpl<ProductUiState, ProductEvent>(
        initialState = ProductUiState(),
        scope = { CoroutineScope(Dispatchers.IO) }
    )

    val presenterHandler = stateProducer.presenterHandler


    init {
        stateProducer.collectEvents { event ->
            when (event) {
                ProductEvent.IncreaseCount -> increaseProductCount()
                ProductEvent.DecreaseCount -> decreaseProductCount()
                is ProductEvent.SelectColor -> selectProductColor(color = event.color)
            }
        }

        fetchProductPriceById(productId = productId)
    }


    private fun increaseProductCount() {
        stateProducer.update { uiState ->
            uiState.copy(count = uiState.count + 1)
        }
    }

    private fun decreaseProductCount() {
        val currentCount = stateProducer.value.count
        if (currentCount == 0) return

        stateProducer.update { uiState ->
            uiState.copy(count = uiState.count - 1)
        }
    }

    private fun selectProductColor(color: Color) {
        stateProducer.update { uiState ->
            uiState.copy(
                selectedProductColor = color
            )
        }
    }

    private fun fetchProductPriceById(productId: Long) {
        viewModelScope.launch {
            stateProducer.update { uiState ->
                uiState.copy(loading = true)
            }

            // Simulate a network call
            delay(800L)

            stateProducer.update { uiState ->
                uiState.copy(
                    product = Utils.product,
                    loading = false
                )
            }
        }
    }
}