package com.uzuu.jetpack_compose_hub.feature.ztest.navigationQ315

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

// ===== IMPORTS (thêm vào đầu file) =====
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
// ===== 1. DEPENDENCIES (build.gradle) =====
// implementation "androidx.navigation:navigation-compose:2.7.7"
// implementation "androidx.compose.material:material-icons-extended:1.6.0"

// ===== 2. MAIN ACTIVITY =====
class navigationQueen315 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFF4CAF50), // Màu xanh lá giống video
                    surface = Color.White,
                    background = Color(0xFFF5F5F5) // Màu nền xám nhạt
                )
            ) {
                ShoppingApp()
            }
        }
    }
}

// ===== 3. SEALED CLASS CHO CÁC MÀN HÌNH =====
sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : Screen("home", "Trang chủ", Icons.Default.Home)
    object Shopping : Screen("shopping", "Mua sắm", Icons.Default.ShoppingBag)
    object Community : Screen("community", "Cộng đồng", Icons.Default.People)
    object Messages : Screen("messages", "Tin nhắn", Icons.Default.Email)
    object Account : Screen("account", "Tài khoản", Icons.Default.Person)
}

// ===== 4. MAIN APP COMPOSABLE =====
@Composable
fun ShoppingApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            CustomBottomNavigation(navController = navController)
        },
        containerColor = Color(0xFFF5F5F5) // SỬA: dùng containerColor thay vì backgroundColor
    ) { paddingValues ->
        SetupNavigation(
            navController = navController,
            paddingValues = paddingValues
        )
    }
}

// ===== 5. CUSTOM BOTTOM NAVIGATION =====
// ===== 5. CUSTOM BOTTOM NAVIGATION - GIỐNG VIDEO =====
@Composable
fun CustomBottomNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Gray,
        tonalElevation = 8.dp
    ) {
        val screens = listOf(
            Screen.Home,
            Screen.Shopping,
            Screen.Community,
            Screen.Messages,
            Screen.Account
        )

        screens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = screen.title,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF4CAF50), // Màu xanh khi chọn
                    selectedTextColor = Color(0xFF4CAF50), // Màu xanh khi chọn
                    unselectedIconColor = Color.Gray, // Màu xám khi không chọn
                    unselectedTextColor = Color.Gray, // Màu xám khi không chọn
                    indicatorColor = Color.Transparent // Không có background
                )
            )
        }
    }
}

// ===== 6. SETUP NAVIGATION - KHÔNG ANIMATION =====
@Composable
fun SetupNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(paddingValues)
        // KHÔNG có animation - chuyển ngay như video
    ) {
        composable(Screen.Home.route) {
            HomeScreen315()
        }
        composable(Screen.Shopping.route) {
            ShoppingScreen315()
        }
        composable(Screen.Community.route) {
            CommunityScreen315()
        }
        composable(Screen.Messages.route) {
            MessagesScreen315()
        }
        composable(Screen.Account.route) {
            AccountScreen315()
        }
    }
}