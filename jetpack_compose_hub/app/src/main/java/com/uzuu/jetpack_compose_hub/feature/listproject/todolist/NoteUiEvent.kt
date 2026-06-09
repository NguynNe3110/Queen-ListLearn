package com.uzuu.jetpack_compose_hub.feature.listproject.todolist

sealed class NoteUiEvent {
    data class Toast(val message : String) : NoteUiEvent()

    object ShowDialog : NoteUiEvent()
    object DissmissDialog : NoteUiEvent()
}