package com.example.mamamboga.navigation

sealed class Route(val route:String){
    object Home:Route("home")
    object OrderSummary:Route("success")
}