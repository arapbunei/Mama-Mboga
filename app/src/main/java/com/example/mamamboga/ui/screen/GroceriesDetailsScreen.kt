package com.example.mamamboga.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mamamboga.R
import com.example.mamamboga.data.DataProvider


@Composable
fun GroceriesDetailsScreen(
    navController: NavController,
    groceryId: Long
) {
    val orderGrocery = requireNotNull(DataProvider.BasketGrocery(groceryId))

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // TODO: fix contentDescription
        TopAppBar(
            title = {  },
            navigationIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 8.dp)
                        .clickable {
                            navController.navigateUp()
                        }
                )
            },
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        )
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = orderGrocery.grocery.name,
                fontSize = 42.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}