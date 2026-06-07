package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.feature.columnQueen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScreen(
    uiState: ColumnUiState,
    onItemClicked: (ColumnItem) -> Unit
) {
    when (uiState) {
        is ColumnUiState.Loading -> LoadingView()
        is ColumnUiState.Error -> Errorview(uiState.message)

        is ColumnUiState.Success -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Duyệt qua TẤT CẢ items trong 1 vòng lặp duy nhất
                items(
                    items = uiState.items,
                    key = { it.id }
                ) { item ->
                    // Logic quyết định vẽ gì dựa trên ID
                    if (item.id % 2 == 0) {
                        // ID Chẵn -> Vẽ Card Lỗi
                        ItemErrorCard(item = item)
                        Log.d("DEBUG", "in columnScreen even")
                    } else {
                        // ID Lẻ -> Vẽ Card Thường
                        ItemCard(
                            item = item,
                            onClick = { onItemClicked(item) }
                        )
                        Log.d("DEBUG", "in columnScreen odd")
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

// Component hiển thị Item bình thường (Success)
@Composable
private fun ItemCard(item: ColumnItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// Component hiển thị Item bị lỗi (Error)
@Composable
private fun ItemErrorCard(item: ColumnItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.1f)), // Nền đỏ nhạt
        border = BorderStroke(1.dp, Color.Red)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "⚠️ ${item.title}",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Item này có ID chẵn nên bị đánh dấu lỗi.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun Errorview(mess : String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = mess, color = Color.Red)
    }
}