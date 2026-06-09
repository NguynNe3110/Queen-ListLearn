package com.uzuu.jetpack_compose_hub.feature.listproject.todolist

data class DialogUiState (
    val isDialogVisiable: Boolean = false,
    val content: String = "",
    val error: String = ""
)