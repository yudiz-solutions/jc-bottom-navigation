package com.example.composebottomnavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composebottomnavigation.Constants.ROUTE_FAVORITE
import com.example.composebottomnavigation.Constants.ROUTE_HOME
import com.example.composebottomnavigation.Constants.ROUTE_NOTIFICATION
import com.example.composebottomnavigation.Constants.ROUTE_SETTING
import com.example.composebottomnavigation.screen.Favorite
import com.example.composebottomnavigation.screen.Home
import com.example.composebottomnavigation.screen.Notification
import com.example.composebottomnavigation.screen.Screens.Items.items
import com.example.composebottomnavigation.screen.Settings

@Composable
fun NavigationController() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                elevation = 10.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Bottom Navigation Demo")
                }
            }
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.background,
                elevation = 16.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()           //when ever backStack changes it will recompose itself
                val currentRoute = navBackStackEntry?.destination?.route                        //fetching current backStack entry

                items.forEach {
                    BottomNavigationItem(
                        icon = {                                                                //bottom nav icon
                            Icon(imageVector = it.icon, contentDescription = "",tint = if (currentRoute == it.route) Color.DarkGray else Color.LightGray)
                        },
                        selected = currentRoute == it.route,                                    //current destination that is visible to user
                        label = {
                            Text(                                                               //bottom nav text
                                text = it.label,
                                color = if (currentRoute == it.route) Color.DarkGray else Color.LightGray,
                                maxLines = 1,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        },
                        onClick = {
                            if (currentRoute != it.route) {                                     //current route is not equal to same route
                                navController.graph.startDestinationRoute?.let { item ->        //then handle back press
                                    navController.popBackStack(
                                        item, false
                                    )
                                }
                            }
                            if (currentRoute != it.route) {                                     //condition to check current route is not equal to screens route
                                navController.navigate(it.route)
                            }
                        },
                        alwaysShowLabel = false,                                                 // showing/hiding title text
                        selectedContentColor = MaterialTheme.colors.secondary,                  // ripple color
                    )
                }

            }
        }
    ) {
        ScreenController(navController = navController)
    }
}

@Composable
fun ScreenController(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = ROUTE_HOME) {
        composable(ROUTE_HOME) {
            Home()
        }
        composable(ROUTE_NOTIFICATION) {
            Notification()
        }
        composable(ROUTE_FAVORITE) {
            Favorite()
        }
        composable(ROUTE_SETTING) {
            Settings()
        }
    }
}