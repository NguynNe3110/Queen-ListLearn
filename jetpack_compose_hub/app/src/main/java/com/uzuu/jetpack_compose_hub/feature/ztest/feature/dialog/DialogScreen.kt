package com.uzuu.jetpack_compose_hub.feature.ztest.feature.dialog

// File: DialogScreen.kt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp

@Composable
fun DialogScreen(viewModel: DialogViewModel = viewModel()) {
    // 1. Collect state từ ViewModel
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        // 2. Nút bấm gửi Event ShowDialog
        Button(onClick = { viewModel.onEvent(DialogUiEvent.ShowDialog) }) {
            Text("Hiện Dialog")
        }

        // 3. Render Dialog dựa trên State (Conditional Rendering)
        if (uiState.isDialogVisible) {
            AlertDialog(
                onDismissRequest = {
                    // Bắt buộc phải xử lý khi người dùng nhấn ra ngoài hoặc nút Back
                    viewModel.onEvent(DialogUiEvent.DismissDialog)
                },
                title = { Text(text = "Xác nhận") },
                text = { Text(text = "Bạn có chắc chắn muốn thực hiện hành động này không?") },
                confirmButton = {
                    TextButton(onClick = {
                        // Xử lý logic khi đồng ý, sau đó đóng dialog
                        viewModel.onEvent(DialogUiEvent.DismissDialog)
                    }) {
                        Text("Đồng ý")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        viewModel.onEvent(DialogUiEvent.DismissDialog)
                    }) {
                        Text("Hủy")
                    }
                }
            )
        }
    }
}