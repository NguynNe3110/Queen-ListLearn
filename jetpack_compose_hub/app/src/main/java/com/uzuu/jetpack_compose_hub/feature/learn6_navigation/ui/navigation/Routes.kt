package com.uzuu.jetpack_compose_hub.feature.learn6_navigation.ui.navigation

import okhttp3.Route


//dinh nghia huong di, man hinh

sealed class Screen(val route: String) {
    object Splash : Screen("splash")

    // Auth group
    object Login : Screen("login")
    object Register : Screen("register")

    // Main Screen với Bottom Navigation
    object Main : Screen("main")

    // Các tab trong Bottom Navigation
    object Home : Screen("home")
    object Setting : Screen("setting")
    object Profile : Screen("profile")
}