package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.feature.lazy_columnQueen

sealed class ColumnUiState {
    object Loading : ColumnUiState()
    data class Success(val items: List<ColumnItem>) : ColumnUiState()
    data class Error(val message: String) : ColumnUiState()
}