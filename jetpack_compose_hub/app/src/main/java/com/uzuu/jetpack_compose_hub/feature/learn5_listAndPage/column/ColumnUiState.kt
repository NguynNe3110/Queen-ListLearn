package com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.column

sealed class ColumnUiState {
    object Loading: ColumnUiState()
    data class Success(val items: List<ColumnItem>) : ColumnUiState()
    data class Error(val mess: String) : ColumnUiState()
}