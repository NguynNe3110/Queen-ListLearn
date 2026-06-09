package com.uzuu.jetpack_compose_hub.feature.listproject.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiStateDialog = MutableStateFlow(DialogUiState())
    val uiStateDialog = _uiStateDialog.asStateFlow()

    private val _uiEvent = MutableSharedFlow<NoteUiEvent>(extraBufferCapacity = 3)
    val uiEvent = _uiEvent.asSharedFlow()

    fun onClickFAB() {

    }

    fun DialogShow() {
        _uiStateDialog.update {
            it.copy(isDialogVisiable = true)
        }
    }

    fun DialogDismiss() {
        _uiStateDialog.update {
            it.copy(isDialogVisiable = false)
        }
    }
}