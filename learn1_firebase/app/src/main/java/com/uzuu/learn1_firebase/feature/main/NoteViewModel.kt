package com.uzuu.learn1_firebase.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uzuu.learn1_firebase.core.result.ApiResult
import com.uzuu.learn1_firebase.domain.model.Note
import com.uzuu.learn1_firebase.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UiState(
    val isLoading: Boolean = false,
    val error : String = "",
    val notes: List<Note> = emptyList()
)

sealed class UiEvent(){
    data class Toast(val message: String) : UiEvent()
}

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepo: NoteRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>(extraBufferCapacity = 3)
    val uiEvent = _uiEvent.asSharedFlow()

    fun onAdd(titlle: String, content: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            when(val data = noteRepo.addNote(Note(title= titlle, content = content))) {
                is ApiResult.Success -> {
                    val idnote = data.data
                    _uiState.update { it.copy(isLoading = false, error = "") }
                }
                is ApiResult.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = "lỗi : ${data.message}") }
                }
            }
        }
    }
}