package com.uzuu.jetpack_compose_hub.feature.ztest.feature.dialog

// File: DialogUiState.kt
data class DialogUiState(
    val isDialogVisible: Boolean = false
)

// File: DialogUiEvent.kt
sealed class DialogUiEvent {
    object ShowDialog : DialogUiEvent()
    object DismissDialog : DialogUiEvent()
}