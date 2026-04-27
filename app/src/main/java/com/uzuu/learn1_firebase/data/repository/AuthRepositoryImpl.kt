package com.uzuu.learn1_firebase.data.repository

import com.uzuu.learn1_firebase.data.remote.datasource.FirebaseAuthDataSource
import com.uzuu.learn1_firebase.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: FirebaseAuthDataSource
) : AuthRepository{
    override suspend fun login(email: String, password: String) {
        dataSource.login(email, password)
    }

    override suspend fun register(email: String, password: String) {
        dataSource.register(email, password)
    }

    override fun logout() {
        dataSource.logout()
    }
}