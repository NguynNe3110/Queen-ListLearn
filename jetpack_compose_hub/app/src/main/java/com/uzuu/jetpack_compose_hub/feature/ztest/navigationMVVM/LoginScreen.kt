package com.uzuu.jetpack_compose_hub.feature.ztest.navigationMVVM

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit // Nhận callback từ nơi chứa NavController (ví dụ: MainActivity)
) {
    val state by viewModel.uiState.collectAsState()

    // State cục bộ của Compose để giữ text người dùng đang nhập
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )

        Button(
            onClick = {
                // KHI CLICK:
                // 1. Truyền DỮ LIỆU (username, password) xuống để xử lý nghiệp vụ.
                // 2. Truyền CALLBACK (onNavigateToHome) để ViewModel kích hoạt nếu thành công.
                viewModel.onLoginClick(
                    username = username,
                    password = password,
                    onNavigateToHome = onNavigateToHome
                )
            },
            enabled = !state.isLoading
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
            } else {
                Text("Login")
            }
            // Bạn có thể dùng state.errorMessage ở đây để hiện Snackbar/Text lỗi
        }
    }
}