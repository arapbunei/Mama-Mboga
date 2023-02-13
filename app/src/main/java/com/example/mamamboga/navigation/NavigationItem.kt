package com.example.mamamboga.navigation

import androidx.annotation.DrawableRes
import com.example.mamamboga.R

const val GROCERY_KEY = "groceries"
const val GROCERY_DETAILS_KEY = "groceriesDetails"
const val BASKET_KEY = "basket"
const val PROFILE_KEY = "profile"


sealed class NavigationItem(val route: String, val routesIncluded: List<String>, @DrawableRes val icon: Int) {
    object Groceries : NavigationItem(GROCERY_KEY, listOf("$GROCERY_DETAILS_KEY/{groceryId}"), R.drawable.b1)
    object GroceriesDetails: NavigationItem("$GROCERY_DETAILS_KEY/{groceryId}", emptyList(), R.drawable.b2) {
        fun createRoute(groceryId: Long) = "$GROCERY_DETAILS_KEY/$groceryId"
    }
    object Basket : NavigationItem(BASKET_KEY,  emptyList(), R.drawable.ic_basket)
    object Profile : NavigationItem(PROFILE_KEY,  emptyList(), R.drawable.ic_profile)
}