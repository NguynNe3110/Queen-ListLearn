package com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.feature.rowQueen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowQScreen(
    viewModel: RowQViewModel = hiltViewModel(),
    onNavigateToDetail: (Int) -> Unit,  // Callback điều hướng
    onShowSnackbar: (String) -> Unit    // Callback hiển thị snackbar
) {
    // 1. Collect StateFlow → Compose State
    val uiState by viewModel.uiState.collectAsState()

    // 2. Collect Channel Event → Xử lý one-time action
    LaunchedEffect(Unit) {
        viewModel.uiEvent
            .onEach { event ->
                when (event) {
                    is RowQUiEvent.DataLoaded -> {
                        // Có thể log hoặc làm gì đó khi load xong
                    }
                    is RowQUiEvent.ShowError -> {
                        onShowSnackbar(event.message)
                    }
                    is RowQUiEvent.NavigateToDetail -> {
                        onNavigateToDetail(event.itemId)
                    }
                    is RowQUiEvent.ShowSnackbar -> {
                        onShowSnackbar(event.message)
                    }
                }
            }
            .launchIn(this)
    }

    // 3. Render UI theo State
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LazyRow + State + Event") },
                actions = {
                    if (!uiState.isLoading && uiState.items.isNotEmpty()) {
                        IconButton(onClick = { viewModel.onRefresh() }) {
                            Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.Refresh,
                                contentDescription = "Refresh"
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                uiState.errorMessage != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = uiState.errorMessage ?: "Unknown error", // yeeu caauf not null
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { viewModel.onRefresh() }) {
                                Text("Thử lại")
                            }
                        }
                    }
                }

                uiState.items.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Không có dữ liệu")
                    }
                }

                else -> {
                    // LAZYROW - DANH SÁCH NGANG
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(
                            items = uiState.items,
                            key = { item -> item.id } // 🔑 BẮT BUỘC
                        ) { item ->
                            RowQItemCard(
                                item = item,
                                onClick = { viewModel.onItemClicked(item) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RowQItemCard(
    item: RowItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(
                        color = Color(item.color),
                        shape = RoundedCornerShape(8.dp)
                    )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}