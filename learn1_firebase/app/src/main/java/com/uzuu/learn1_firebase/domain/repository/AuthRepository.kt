package com.uzuu.learn1_firebase.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String) :String?

    suspend fun register(email: String, password: String): String?

    fun logout()
}