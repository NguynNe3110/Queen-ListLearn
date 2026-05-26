package com.uzuu.learn1_firebase.data.repository

import com.uzuu.learn1_firebase.core.result.ApiResult
import com.uzuu.learn1_firebase.data.remote.datasource.FirebaseAuthDataSource
import com.uzuu.learn1_firebase.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: FirebaseAuthDataSource
) : AuthRepository {

    override suspend fun login(email: String, password: String): ApiResult<Unit> {
        return dataSource.login(email, password) // Chỉ forward, không làm gì thêm
    }

    override suspend fun register(email: String, password: String): ApiResult<Unit>  {
        return dataSource.register(email, password) // forward kết quả
    }

    override fun logout() {
        dataSource.logout()
    }
}