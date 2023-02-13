package com.example.mamamboga.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamamboga.data.DataProvider
import com.example.mamamboga.data.model.OrderGrocery


@Composable
fun ProductCounter(
    orderGrocery: OrderGrocery,
    onProductIncreased: (Long) -> Unit,
    onProductDecreased: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(size = 5.dp),
        border = BorderStroke(1.dp, Color.Gray),
        color = Color.Transparent,
        modifier = Modifier.size(width = 40.dp, height = 85.dp).composed { modifier },
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "＋",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.height(36.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        onProductIncreased(orderGrocery.grocery.id)
                    }
            )
            Text(
                text = orderGrocery.count.toString(),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterHorizontally),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "—",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.height(36.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        onProductDecreased(orderGrocery.grocery.id)
                    }
            )
        }
    }
}

@Preview
@Composable
fun ProductCounter_Preview() {
    val product = OrderGrocery(
        grocery = DataProvider.VEGGIES,
        count = 42
    )
    ProductCounter(
        orderGrocery = product,
        onProductIncreased = { },
        onProductDecreased = { }
    )
}

