package com.uzuu.jetpack_compose_hub.feature.ztest.navigationMVVM

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.uzuu.jetpack_compose_hub.feature.learn6_navigation.feature.main.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

// 1. @AndroidEntryPoint để Hilt có thể inject dependencies vào Activity này
@AndroidEntryPoint
class NavigationMVVMActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. setContent là nơi bắt đầu vẽ UI bằng Jetpack Compose
        setContent {
            MaterialTheme {
                // Gọi hàm chứa NavHost để quản lý điều hướng
                AppNavigation()
            }
        }
    }
}

// 3. Tách riêng hàm Composable chứa NavHost để code gọn gàng và dễ test
@Composable
fun AppNavigation(
    // Có thể inject ViewModel cấp cao nhất nếu cần chia sẻ state giữa nhiều màn hình
    // viewModel: SharedViewModel = hiltViewModel()
) {
    // Khởi tạo NavController để quản lý back stack
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login" // Màn hình khởi đầu
    ) {
        // --- Màn hình Login ---
        composable(route = "login") {
            // Lấy ViewModel của màn hình Login (Hilt tự động inject)

            val loginViewModel: LoginViewModel = hiltViewModel() /////

            LoginScreen(
                viewModel = loginViewModel,
                // Truyền callback điều hướng từ NavHost xuống Composable
                onNavigateToHome = {
                    navController.navigate("home") {
                        // Xóa màn hình login khỏi back stack để người dùng không quay lại được bằng nút Back
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // --- Màn hình Home ---
//        composable(route = "home") {
//            val homeViewModel: HomeViewModel = hiltViewModel()
//
//            HomeScreen(
//                viewModel = homeViewModel,
//                onNavigateToDetail = { itemId ->
//                    // Ví dụ điều hướng có kèm tham số (argument)
//                    navController.navigate("detail/$itemId")
//                }
//            )
//        }

        // --- Màn hình Detail (Ví dụ nhận tham số) ---
//        composable(route = "detail/{itemId}") { backStackEntry ->
//            // Lấy tham số itemId từ route
//            val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
//            val detailViewModel: DetailViewModel = hiltViewModel()
//
//            DetailScreen(
//                viewModel = detailViewModel,
//                itemId = itemId,
//                onNavigateBack = {
//                    navController.popBackStack() // Quay lại màn hình trước đó
//                }
//            )
//        }
    }
}