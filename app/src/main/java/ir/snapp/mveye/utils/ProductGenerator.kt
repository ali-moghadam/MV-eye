package ir.snapp.mveye.utils

import ali.rnp.mveye.R
import androidx.compose.ui.graphics.Color
import ir.snapp.mveye.model.Product

object ProductGenerator {

    const val DEFAULT_PRODUCT_ID : Long = 1

    fun sampleProduct(): Product {
        return Product(
            id = DEFAULT_PRODUCT_ID,
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

    fun allProducts() : List<Product> {
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
                price = 390.99,
                colors = listOf(
                    Color.Gray,
                    Color.Blue,
                    Color.Green,
                ),
                image = R.drawable.headphone
            )
        )
    }

    fun product(productId: Long) : Product {
        return allProducts()
            .find { it.id == productId } ?: sampleProduct()
    }
}