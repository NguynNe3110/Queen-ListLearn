package com.uzuu.jetpack_compose_hub.feature.ztest.navigationMVVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    // 1. UiState CHỈ giữ trạng thái của màn hình (loading, error, data)
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    // 2. Hàm nhận DỮ LIỆU (user, pass) để xử lý,
    //    ĐỒNG THỜI nhận một CALLBACK (onNavigateToHome) để điều hướng nếu thành công
    fun onLoginClick(
        username: String,
        password: String,
        onNavigateToHome: () -> Unit // <-- Đây là chìa khóa
    ) {
        viewModelScope.launch {
            // Cập nhật state đang loading
            _uiState.value = _uiState.value.copy(isLoading = true)

            // Gọi xuống Data Layer (Repository) để xử lý dữ liệu
            val result = authRepository.login(username, password)

            if (true) { // phải check result.isSuccess
                // 3. XỬ LÝ THÀNH CÔNG:
                // Không cần set isLoginSuccess = true vào UiState.
                // Thay vào đó, gọi thẳng callback để UI tự lo việc chuyển màn hình.
                onNavigateToHome()
            } else {
                // 4. XỬ LÝ THẤT BẠI:
                // Cập nhật UiState để Compose tự vẽ lại và hiện thông báo lỗi
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Sai tài khoản hoặc mật khẩu"
                )
            }
        }
    }
}

class AuthRepository @Inject constructor() {
    fun login(username: String, password: String) {
        // todo code
    }
}