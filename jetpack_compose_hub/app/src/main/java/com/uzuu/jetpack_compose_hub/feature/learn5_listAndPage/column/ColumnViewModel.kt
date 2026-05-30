package com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.column

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColumnViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<ColumnUiState>(ColumnUiState.Loading)
    val uiState: StateFlow<ColumnUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            delay(1000)
            val fakeItems = (1..50).map { index->
                ColumnItem(
                    id = index,
                    title = "San pham #$index",
                    description = "Mo ta ngan gon cho san pham so $index"
                )
            }
            for (item in fakeItems){
                if(item.id % 2 == 0) {
                    _uiState.update {

                    }
                } else {

                }
            }

        }
    }
}