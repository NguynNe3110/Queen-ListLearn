package com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.feature.rowChat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun RowCNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            RowCScreen(
                navController = navController
            )
        }

        composable(
            route = "detail/{name}"
        ) {
            val name =
                it.arguments?.getString("name") ?: ""
            RowCDetailScreen(name)
        }
    }
}