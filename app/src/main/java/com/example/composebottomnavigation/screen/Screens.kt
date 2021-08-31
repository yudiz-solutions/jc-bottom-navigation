package com.example.composebottomnavigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composebottomnavigation.Constants.ROUTE_FAVORITE
import com.example.composebottomnavigation.Constants.ROUTE_HOME
import com.example.composebottomnavigation.Constants.ROUTE_NOTIFICATION
import com.example.composebottomnavigation.Constants.ROUTE_SETTING
import com.example.composebottomnavigation.R

sealed class Screens(val route: String, var label: String, val icon: ImageVector) {

    object Home : Screens(ROUTE_HOME, "Home", Icons.Default.Home)
    object Notification : Screens(ROUTE_NOTIFICATION, "Notification", Icons.Default.Notifications)
    object Favorite : Screens(ROUTE_FAVORITE, "Favorite", Icons.Default.Favorite)
    object Setting : Screens(ROUTE_SETTING, "Setting", Icons.Default.Settings)

    object Items {
        val items = listOf(
            Home, Notification, Favorite, Setting
        )
    }
}