package com.uzuu.jetpack_compose_hub.feature.ztest.feature.dialog

// File: DialogViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DialogViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DialogUiState())
    val uiState: StateFlow<DialogUiState> = _uiState.asStateFlow()

    fun onEvent(event: DialogUiEvent) {
        when (event) {
            is DialogUiEvent.ShowDialog -> {
                _uiState.update { it.copy(isDialogVisible = true) }
            }
            is DialogUiEvent.DismissDialog -> {
                _uiState.update { it.copy(isDialogVisible = false) }
            }
        }
    }
}