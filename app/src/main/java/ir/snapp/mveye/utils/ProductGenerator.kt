package ir.snapp.mveye.utils

import androidx.compose.ui.graphics.Color
import ir.snapp.mveye.R
import ir.snapp.mveye.model.Product

object ProductGenerator {

    const val DEFAULT_PRODUCT_ID: Long = 0

    fun product(): Product {
        return allProducts().first()
    }

    fun product(productId: Long): Product {
        return allProducts()
            .find { it.id == productId } ?: product()
    }

    private fun allProducts(): List<Product> {
        return listOf(
            Product(
                id = 0,
                name = "Beats Studio Pro",
                price = 349.99,
                colors = listOf(
                    Color.Black,
                    Color.Blue,
                    Color.Red,
                ),
                image = R.drawable.headphone
            ),
            Product(
                id = 1,
                name = "Beats Studio Pro 2",
                price = 200.99,
                colors = listOf(
                    Color.Gray,
                    Color.Blue,
                    Color.Green,
                ),
                image = R.drawable.headphone
            )
        )
    }
}