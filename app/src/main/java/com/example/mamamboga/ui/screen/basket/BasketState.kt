package com.example.mamamboga.ui.screen.basket

import com.example.mamamboga.data.model.OrderGrocery
import java.math.BigDecimal

data class BasketState(
    val orderGroceries: List<OrderGrocery>,
    val totalPrice: BigDecimal
)