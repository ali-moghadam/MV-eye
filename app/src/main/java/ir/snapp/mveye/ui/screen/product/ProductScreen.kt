package ir.snapp.mveye.ui.screen.product

import ali.rnp.mveye.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ir.snapp.mveye.api.MvStateConsumer
import ir.snapp.mveye.contract.ProductEvent
import ir.snapp.mveye.contract.ProductUiState
import ir.snapp.mveye.ui.component.ProductColorComponent

@Composable
internal fun ProductScreen(
    mvStateConsumer: MvStateConsumer<ProductUiState, ProductEvent>,
) {
    val uiState by mvStateConsumer.uiState.collectAsState()

    val onIncreaseClick = remember {
        { mvStateConsumer.onEvent(ProductEvent.IncreaseCount) }
    }

    val onDecreaseClick = remember {
        { mvStateConsumer.onEvent(ProductEvent.DecreaseCount) }
    }

    val onColorSelected = remember {
        { color: Color ->
            mvStateConsumer.onEvent(ProductEvent.SelectColor(color))
        }
    }

    Surface(
        color = Color(0xFFF4F4F4),
    ) {
        Box {
            if (uiState.loading || uiState.product == null) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                Column(
                    modifier = Modifier.scrollable(rememberScrollState(), Orientation.Vertical)
                ) {
                    Image(
                        modifier = Modifier.aspectRatio(1f).padding(20.dp),
                        painter = painterResource(id = uiState.product!!.image),
                        contentDescription = null
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(
                                    topStart = 25.dp,
                                    topEnd = 25.dp,
                                )
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(text = uiState.product!!.name)

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                IconButton(onClick = onDecreaseClick) {
                                    Icon(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .rotate(180f),
                                        painter = painterResource(id = R.drawable.chevron_direction_top_icon),
                                        contentDescription = null,
                                    )
                                }

                                Text(
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    text = uiState.count.toString(),
                                )

                                IconButton(onClick = onIncreaseClick) {
                                    Icon(
                                        modifier = Modifier.size(20.dp),
                                        painter = painterResource(id = R.drawable.chevron_direction_top_icon),
                                        contentDescription = null,
                                    )
                                }
                            }

                            ProductColorComponent(
                                colors = uiState.product!!.colors,
                                selectedColor = uiState.selectedProductColor
                                    ?: uiState.product!!.colors.first(),
                                onColorSelect = onColorSelected,
                            )
                        }
                    }
                }
            }
        }
    }
}