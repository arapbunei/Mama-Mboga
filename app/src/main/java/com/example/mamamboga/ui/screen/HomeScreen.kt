package com.example.mamamboga.ui.screen


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.compose.material.Icon
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.mamamboga.navigation.NavigationItem
import com.example.mamamboga.navigation.Route
import com.example.mamamboga.navigation.Router
import com.example.mamamboga.navigation.currentRoute
import com.example.mamamboga.ui.screen.groceries.GroceryScreen
import com.example.mamamboga.ui.screen.basket.BasketScreen
import com.example.mamamboga.ui.theme.MamaMbogaTheme

@Composable
fun HomeScreen(
    externalRouter: Router
) {
    val tabs = listOf(
        NavigationItem.Groceries,
        NavigationItem.Basket,
        NavigationItem.Profile
    )

    val navController = rememberNavController()

    MamaMbogaTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    tabs.forEach { navigationItem ->
                        val currentRoute = navController.currentRoute()

                        BottomNavigationItem(
                            selected = currentRoute == navigationItem.route || navigationItem.routesIncluded.contains(currentRoute),
                            onClick = {
                                if (currentRoute != navigationItem.route) {
                                    navController.navigate(navigationItem.route)
                                }
                            },
                            label = null,
                            icon = {
                                // TODO: fix contentDescription
                                Icon(
                                    painter = painterResource(id = navigationItem.icon),
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())) {
                val uri = "https://example.com"

                NavHost(navController = navController, startDestination = NavigationItem.Groceries.route) {
                    composable(NavigationItem.Groceries.route) {
                        GroceryScreen(
                            navigateToGroceriesDetails = {
                                navController.navigate(NavigationItem.GroceriesDetails.createRoute(it))
                            }
                        )
                    }

                    composable(
                        route = NavigationItem.GroceriesDetails.route,
                        arguments = listOf(navArgument("groceryId") { type = NavType.LongType } ),
                        deepLinks = listOf(navDeepLink { uriPattern = "$uri/GroceryDetails/groceryId={groceryId}" })
                    ) {
                        GroceriesDetailsScreen(
                            navController = navController,
                            groceryId = it.arguments?.getLong("groceryId") ?: -1L
                        )
                    }

                    composable(NavigationItem.Basket.route) {
                        BasketScreen(
                            navigateToSuccess = { externalRouter.navigateTo(Route.OrderSummary.route) }
                        )
                    }

                    composable(NavigationItem.Profile.route) {
                        ProfileScreen()
                    }
                }
            }
        }
    }
}