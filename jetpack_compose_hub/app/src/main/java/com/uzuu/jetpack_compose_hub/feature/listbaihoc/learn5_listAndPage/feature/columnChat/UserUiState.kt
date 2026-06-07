package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.feature.columnChat

data class UserUiState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false
)
