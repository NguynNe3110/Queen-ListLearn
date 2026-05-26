package com.uzuu.learn1_firebase.domain.repository

import com.uzuu.learn1_firebase.core.result.ApiResult

interface AuthRepository {
    suspend fun login(email: String, password: String) :ApiResult<Unit>

    suspend fun register(email: String, password: String): ApiResult<Unit>

    fun logout()
}