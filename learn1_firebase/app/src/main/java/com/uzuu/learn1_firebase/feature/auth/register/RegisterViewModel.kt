package com.uzuu.learn1_firebase.feature.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uzuu.learn1_firebase.core.result.ApiResult
import com.uzuu.learn1_firebase.domain.repository.AuthRepository
import com.uzuu.learn1_firebase.feature.auth.loginRegister.LoginUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RegisterUiEvent(){
    data class Toast(val msg: String) : RegisterUiEvent()
    object navigateToHome: RegisterUiEvent()
}

data class RegisterUiState(
    val isLoading: Boolean = false,
    val error: String ?= null
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepo : AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<RegisterUiEvent>(extraBufferCapacity = 2)
    val uiEvent = _uiEvent.asSharedFlow()

    private val _channel = Channel<RegisterUiEvent>()
    val channel = _channel.receiveAsFlow()

    fun onRegister(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.update { it.copy(error = "Vui lòng nhập đủ thông tin!") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when(val result = authRepo.register(email, password)){

                is ApiResult.Success -> {
                    _uiState.update { it.copy(isLoading = false) }
                    _channel.send(RegisterUiEvent.navigateToHome)
                    _uiEvent.tryEmit(RegisterUiEvent.Toast("Đăng nhập thành công"))
                }

                is ApiResult.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
            }
        }
    }
}