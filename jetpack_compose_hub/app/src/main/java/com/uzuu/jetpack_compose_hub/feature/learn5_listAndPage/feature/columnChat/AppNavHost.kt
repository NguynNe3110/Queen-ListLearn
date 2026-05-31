package com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.feature.columnChat

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "user"
    ) {

        composable("user") {
            UserScreen()
        }
    }
}