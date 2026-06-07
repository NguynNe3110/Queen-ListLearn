package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.feature.rowQueen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RowQViewModel @Inject constructor() : ViewModel() {

    // ========== STATE (trạng thái màn hình) ==========
    private val _uiState = MutableStateFlow(RowQUiState())
    val uiState: StateFlow<RowQUiState> = _uiState.asStateFlow()

    // ========== EVENT (sự kiện one-time) ==========
    private val _uiEvent = Channel<RowQUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadFakeData()
    }

    private fun loadFakeData() {
        viewModelScope.launch {
            // Update state: đang loading
            _uiState.value = RowQUiState(isLoading = true)

            delay(1000) // Giả lập network call

            try {
                val fakeItems = (1..20).map { index ->
                    RowItem(
                        id = index,
                        title = "Item #$index",
                        description = "Mô tả ngắn cho item số $index",
                        color = (index * 123456) % 16777215
                    )
                }

                // Update state: success
                _uiState.value = RowQUiState(
                    isLoading = false,
                    items = fakeItems,
                    errorMessage = null
                )

                // Phát event one-time
                _uiEvent.send(RowQUiEvent.DataLoaded)

            } catch (e: Exception) {
                // Update state: error
                _uiState.value = RowQUiState(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error"
                )

                // Phát event one-time
                _uiEvent.send(RowQUiEvent.ShowError(e.message ?: "Unknown error"))
            }
        }
    }

    // ========== ACTION: Xử lý khi user click item ==========
    fun onItemClicked(item: RowItem) {
        viewModelScope.launch {
            // Phát event điều hướng (one-time)
            _uiEvent.send(RowQUiEvent.NavigateToDetail(item.id))
        }
    }

    // ========== ACTION: Refresh data ==========
    fun onRefresh() {
        loadFakeData()
    }

    // ========== CLEANUP: Đóng channel khi ViewModel bị hủy ==========
    override fun onCleared() {
        super.onCleared()
        _uiEvent.close()
    }
}