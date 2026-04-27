package com.uzuu.learn1_firebase.feature.auth.loginRegister

data class LoginUiState (
    val email: String = "",
    val password : String = "",
    val isLoading : Boolean = false,
    val error: String ?= null
)