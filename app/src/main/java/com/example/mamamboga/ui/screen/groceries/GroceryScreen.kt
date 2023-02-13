package com.example.mamamboga.ui.screen.groceries

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.material.Divider
import androidx.compose.foundation.Image
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamamboga.data.model.OrderGrocery
import com.example.mamamboga.ui.common.UiState
import com.example.mamamboga.ui.component.ProductCounter
import com.example.mamamboga.ui.screen.Groceries.GroceriesViewModel


@Composable
fun GroceryScreen(
    navigateToGroceriesDetails: (Long) -> Unit,
    viewModel: GroceriesViewModel = GroceriesViewModel()
) {
    viewModel.uiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.loadGroceries()
            }
            is UiState.Success -> {
                Column {
                    TopAppBar {
                        Text(
                            text = "Kibandasky",
                            modifier = Modifier.padding(horizontal = 12.dp),
                            fontSize = 18.sp
                        )
                    }
                    GroceryList(
                        items = uiState.data,
                        onGrocery = navigateToGroceriesDetails,
                        onGroceryCountIncreased = {
                            viewModel.addGrocery(it)
                        },
                        onGroceryCountDecreased = {
                            viewModel.removeGrocery(it)
                        }
                    )
                }
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun GroceryList(
    items: List<OrderGrocery>,
    onGrocery: (Long) -> Unit,
    onGroceryCountIncreased: (Long) -> Unit,
    onGroceryCountDecreased: (Long) -> Unit
) {
    LazyColumn {
        items.forEach { item ->
            item {
                GroceryItem(
                    orderGrocery = item,
                    onGrocery = onGrocery,
                    onGroceryCountIncreased = onGroceryCountIncreased,
                    onGroceryCountDecreased = onGroceryCountDecreased
                )
                Divider()
            }
        }
    }
}

@Composable
fun GroceryItem(
    orderGrocery: OrderGrocery,
    onGrocery: (Long) -> Unit,
    onGroceryCountIncreased: (Long) -> Unit,
    onGroceryCountDecreased: (Long) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onGrocery(orderGrocery.grocery.id)
            }

    ) {
        // TODO: fix contentDescription
        Image(
            painter = painterResource(id = orderGrocery.grocery.image),
            contentDescription = null,
            modifier = Modifier
                .size(96.dp)
                .padding(start = 8.dp)
        )
        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(top = 4.dp)
        ) {
            Text(
                text = orderGrocery.grocery.name,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 22.sp
            )
            Text(
                text = orderGrocery.grocery.description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 8.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
        ) {
            ProductCounter(
                orderGrocery = orderGrocery,
                onProductIncreased = { onGroceryCountIncreased(orderGrocery.grocery.id) },
                onProductDecreased = { onGroceryCountDecreased(orderGrocery.grocery.id) }
            )
        }
    }
}

