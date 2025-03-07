package com.alirnp.mveye

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alirnp.mveye.product.ProductScreen
import com.alirnp.mveye.product.ProductViewModel
import com.alirnp.mveye.ui.theme.MVEyeTheme
import com.alirnp.mveye.utils.extention.hideStatusBar

class MainActivity : ComponentActivity() {

    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()

        setContent {
            MVEyeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductScreen(
                        productViewModel.stateManager
                    )
                }
            }
        }
    }
}