package com.example.mamamboga.ui.screen.basket

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamamboga.ui.common.UiState
import com.example.mamamboga.data.model.OrderGrocery
import com.example.mamamboga.ui.component.ProductCounter
import java.math.BigDecimal

@Composable
fun BasketScreen(
    navigateToSuccess: () -> Unit,
    viewModel: BasketViewModel = BasketViewModel()
) {
    viewModel.uiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.loadGroceries()
            }
            is UiState.Success -> {
                BasketSuccessScreen(
                    uiState.data,
                    navigateToSuccess,
                    addGrocery = { viewModel.addGrocery(it) },
                    removeGrocery = { viewModel.removeGrocery(it) }
                )
            }
            is UiState.Error -> { }
        }
    }
}

@Composable
fun BasketSuccessScreen(
    state: BasketState,
    navigateToSuccess: () -> Unit,
    addGrocery: (Long) -> Unit,
    removeGrocery: (Long) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar {
            Text(
                text = "Basket",
                modifier = Modifier.padding(horizontal = 12.dp),
                fontSize = 18.sp
            )
        }
        PaymentInfo(
            deliveryCosts = BigDecimal(5),
            total = state.totalPrice,
            currency = "Kshs.",
            isPayButtonEnabled = state.orderGroceries.isNotEmpty(),
            onPayed = navigateToSuccess
        )
        Spacer(modifier = Modifier.height(8.dp))
        GroceryList(
            orderGroceries = state.orderGroceries,
            onGroceryCountIncreased = removeGrocery,
            onGroceryCountDecreased = addGrocery
        )
    }
}

@Composable
private fun PaymentInfo(
    deliveryCosts: BigDecimal,
    total: BigDecimal,
    currency: String,
    isPayButtonEnabled: Boolean,
    onPayed: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        PaymentInfoItem(name = "Delivery Costs:", value = deliveryCosts, currency = currency)
        PaymentInfoItem(name = "Total:", value = total, currency = currency)
        PayButton(isButtonEnabled = isPayButtonEnabled, onPayed = onPayed)
    }
}

@Composable
private fun PaymentInfoItem(
    name: String,
    value: BigDecimal,
    currency: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f),
            fontSize = 16.sp
        )

        Text(
            text = "$currency $value",
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            textAlign = TextAlign.End,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun PayButton(
    isButtonEnabled: Boolean,
    onPayed: () -> Unit
) {
    Button(
        enabled = isButtonEnabled,
        onClick = { onPayed() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Pay",
            fontSize = 24.sp
        )
    }
}

@Composable
private fun GroceryList(
    orderGroceries: List<OrderGrocery>,
    onGroceryCountIncreased: (Long) -> Unit,
    onGroceryCountDecreased: (Long) -> Unit
) {
    LazyColumn {
        orderGroceries.forEach { item ->
            item {
                GroceryItem(
                    orderGrocery = item,
                    onGroceryCountIncreased = onGroceryCountIncreased,
                    onGroceryCountDecreased = onGroceryCountDecreased
                )
                Divider()
            }
        }
    }
}

@Composable
private fun GroceryItem(
    orderGrocery: OrderGrocery,
    onGroceryCountIncreased: (Long) -> Unit,
    onGroceryCountDecreased: (Long) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = orderGrocery.grocery.name,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = orderGrocery.grocery.description,
                fontSize = 14.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            ProductCounter(
                orderGrocery = orderGrocery,
                onProductIncreased = onGroceryCountIncreased,
                onProductDecreased = onGroceryCountDecreased
            )
        }
    }

}




