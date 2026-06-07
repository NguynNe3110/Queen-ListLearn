package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.feature.rowChat

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RowCViewModel @Inject constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow(RowCUiState())

    val uiState = _uiState.asStateFlow()

    init {

        _uiState.value =
            RowCUiState(
                products = listOf(
                    Product(1, "Laptop"),
                    Product(2, "Keyboard"),
                    Product(3, "Mouse"),
                    Product(4, "Monitor"),
                    Product(5, "Headphone")
                )
            )
    }
}