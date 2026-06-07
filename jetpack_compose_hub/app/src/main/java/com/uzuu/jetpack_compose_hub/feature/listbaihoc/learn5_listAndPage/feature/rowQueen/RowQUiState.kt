package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.feature.rowQueen

data class RowQUiState(
    val isLoading: Boolean = false,
    val items: List<RowItem> = emptyList(),
    val errorMessage: String? = null
)