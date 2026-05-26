package com.uzuu.learn1_firebase.feature.auth.loginRegister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uzuu.learn1_firebase.core.result.ApiResult
import com.uzuu.learn1_firebase.domain.repository.AuthRepository
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

@HiltViewModel
class LoginRegisterViewModel @Inject constructor(
    private val authRepo : AuthRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    //sharedFlow
    private val _uiEvent = MutableSharedFlow<LoginUiEvent>(extraBufferCapacity = 3)
    val uiEvent = _uiEvent.asSharedFlow()

    //Channel
    private val _uiEventChannel = Channel<LoginUiEvent>()
    val uiEventChannel = _uiEventChannel.receiveAsFlow()


    fun onEmailChanged(email: String) = _uiState.update { it.copy(email = email) }
    fun onPasswordChanged(password: String) = _uiState.update { it.copy(password = password) }

    fun login(email: String, password: String) {
        if(email.isBlank() || password.isBlank()){
            _uiState.update { it.copy(error = "Vui lòng nhập đủ thông tin!") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = authRepo.login(email, password)) {

                is ApiResult.Success -> {
                    _uiState.update { it.copy(isLoading = false) }
                    _uiEventChannel.send(LoginUiEvent.NavigateToHome)
                    _uiEvent.tryEmit(LoginUiEvent.Toast("Đăng nhập thành công"))
                }
                is ApiResult.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            _uiEventChannel.send(LoginUiEvent.NavigateToRegister)
        }
    }
}