package com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.feature.columnChat

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val fakeUsers = listOf(
            User(1, "Nguyen"),
            User(2, "Tran"),
            User(3, "Le"),
            User(4, "Pham")
        )
        _uiState.update {
            it.copy(users = fakeUsers)
        }
    }
}