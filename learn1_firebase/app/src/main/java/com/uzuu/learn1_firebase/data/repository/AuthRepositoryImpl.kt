// data/repository/AuthRepositoryImpl.kt
package com.uzuu.learn1_firebase.data.repository

import com.uzuu.learn1_firebase.data.remote.datasource.FirebaseAuthDataSource
import com.uzuu.learn1_firebase.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: FirebaseAuthDataSource
) : AuthRepository {

    override suspend fun login(email: String, password: String): String? {
        return dataSource.login(email, password) // forward kết quả
    }

    override suspend fun register(email: String, password: String): String? {
        return dataSource.register(email, password) // forward kết quả
    }

    override fun logout() {
        dataSource.logout()
    }
}