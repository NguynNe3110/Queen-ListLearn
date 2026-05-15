package com.uzuu.learn1_firebase.feature.auth.loginRegister

sealed class LoginUiEvent {
    data class Toast(val msg: String) : LoginUiEvent()
    object NavigateToHome : LoginUiEvent()
    object NavigateToRegister : LoginUiEvent()
}