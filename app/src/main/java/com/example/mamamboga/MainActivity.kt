package com.example.mamamboga

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.mamamboga.ui.screen.summary.OrderSummaryScreen
import com.example.mamamboga.navigation.Route
import com.example.mamamboga.navigation.createRouter
import com.example.mamamboga.ui.screen.HomeScreen
import com.example.mamamboga.ui.theme.MamaMbogaTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MamaMbogaTheme {


                val navController = rememberNavController()
                NavHost(navController=navController,startDestination= Route.Home.route){
                    composable(Route.Home.route){
                        HomeScreen(createRouter { route ->
                            navController.navigate(route)
                        })
                    }
                    composable(route = Route.OrderSummary.route) {
                        OrderSummaryScreen(
                            navigateToHomeScreen = {
                                navController.navigate(Route.Home.route) {
                                    popUpTo(Route.OrderSummary.route) { inclusive = true }
                                }
                            }
                        )
                }
            }
        }
    }
}
}



