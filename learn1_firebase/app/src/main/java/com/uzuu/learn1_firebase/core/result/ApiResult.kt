package com.uzuu.learn1_firebase.core.result

sealed class ApiResult<out T> {
    data class Success<T>(val data: T): ApiResult<T>()
    data class Error(val message: String, val code: String? = null): ApiResult<Nothing>()
}