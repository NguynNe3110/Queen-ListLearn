package com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.column

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.style.Style
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
        is ColumnUiState.Error -> Errorview(uiState.mess)
        is ColumnUiState.Success -> ItemList(
            items = uiState.items,
            onItemClick = onItemClicked
        )
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
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

@Composable
private fun ItemList(
    items: List<ColumnItem>,
    onItemClick: (ColumnItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = items,
            key = { item -> item.id } // 🔑 BẮT BUỘC: key duy nhất để Compose tái sử dụng đúng item
        ) { item ->
            ItemCard(
                item = item,
                onClick = { onItemClick(item) }
            )
        }
    }
}

@Composable
fun ItemCard(item: ColumnItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}