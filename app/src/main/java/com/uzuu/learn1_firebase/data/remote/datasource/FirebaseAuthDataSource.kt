package com.uzuu.learn1_firebase.data.remote.datasource

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthDataSource @Inject constructor() {
    private val auth = FirebaseAuth.getInstance()

    suspend fun login(email: String, password: String) : AuthResult =
        auth.signInWithEmailAndPassword(email, password).await()

    suspend fun register(email: String, password: String) : AuthResult =
        auth.createUserWithEmailAndPassword(email, password).await()

    fun logout() = auth.signOut()
}
