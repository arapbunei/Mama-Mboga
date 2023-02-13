package com.example.mamamboga.data

import com.example.mamamboga.R
import com.example.mamamboga.data.model.Grocery
import com.example.mamamboga.data.model.OrderGrocery
import java.math.BigDecimal

object DataProvider {
    val VEGGIES = Grocery(
        id = 1L,
        name = "Mboga",
        image = R.drawable.ic_veggies,
        description = "Skuma,managu,spinach. ",
        size = "bunch",
        price = BigDecimal(6.5)
    )

    val MEAT = Grocery(
        id = 2L,
        name = "Meat",
        image = R.drawable.ic_home_meats,
        description = "Nyama.",
        size = "1kg",
        price = BigDecimal(6.0)
    )

    val FISH = Grocery(
        id = 3L,
        name = "Fish",
        image = R.drawable.ic_home_fish,
        description = "Samak.",
        size = "medium",
        price = BigDecimal(5.0)
    )

    val EGG = Grocery(
        id = 4L,
        name = "Egg",
        image = R.drawable.ic_egg,
        description = "Mayai Kienyeji.",
        size = "1 tray",
        price = BigDecimal(6.5)
    )

    val FRUIT = Grocery(
        id = 5L,
        name = "Fruits",
        image = R.drawable.b4,
        description = "Matunda.",
        size = "Big",
        price = BigDecimal(6.0)
    )

    val MILK = Grocery(
        id = 6L,
        name = "Mala",
        image = R.drawable.ic_dairy,
        description = "Maziwa mala.",
        size = "Glass",
        price = BigDecimal(6.5)
    )

    val COFFEE = Grocery(
        id = 7L,
        name = "Iced Mocha",
        image = R.drawable.iced_mocha_small,
        description = "Kahawa.",
        size = "glass",
        price = BigDecimal(6.0)
    )

    val COOKIES = Grocery(
        id = 8L,
        name = "Cookies",
        image = R.drawable.ic_cookies,
        description = "Cookies.",
        size = "packed",
        price = BigDecimal(6.0)
    )

    private val SMOKIES = Grocery(
        id = 9L,
        name = "Smokies",
        image = R.drawable.ic_home_meats,
        description = "smokies.",
        size = "small",
        price = BigDecimal(6.5)
    )

    val GREENMAIZE = Grocery(
        id = 10L,
        name = "Green Maize",
        image = R.drawable.ic_home_veggies,
        description = "Green maize",
        size = "medium",
        price = BigDecimal(6.0)
    )

    fun getAllBasketGroceries() = listOf(
        OrderGrocery(VEGGIES, 0),
        OrderGrocery(MEAT, 0),
        OrderGrocery(FISH, 0),
        OrderGrocery(FRUIT, 0),
        OrderGrocery(MILK, 0),
        OrderGrocery(EGG, 0),
        OrderGrocery(COFFEE, 0),
        OrderGrocery(COOKIES, 0),
        OrderGrocery(SMOKIES, 0),
        OrderGrocery(GREENMAIZE, 0)
    )

    fun BasketGrocery(groceryId: Long) : OrderGrocery? {
        return getAllBasketGroceries()
            .firstOrNull { orderCoffeeDrink ->
                orderCoffeeDrink.grocery.id == groceryId
            }
    }
}