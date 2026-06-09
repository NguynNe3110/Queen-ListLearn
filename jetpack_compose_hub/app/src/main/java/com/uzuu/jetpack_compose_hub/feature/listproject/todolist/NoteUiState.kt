package com.uzuu.jetpack_compose_hub.feature.listproject.todolist

data class NoteUiState (
    val isLoading: Boolean = false,
    val items: List<Note> = emptyList()
)