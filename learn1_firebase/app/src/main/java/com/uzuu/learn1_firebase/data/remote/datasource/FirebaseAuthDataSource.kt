package com.uzuu.learn1_firebase.data.remote.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.uzuu.learn1_firebase.core.result.ApiResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthDataSource @Inject constructor() {
    private val auth = FirebaseAuth.getInstance()

    suspend fun login(email: String, password: String): ApiResult<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            ApiResult.Success(Unit) // thành công, không cần trả data
        } catch (e: Exception) {
            ApiResult.Error(
                message = mapFirebaseError(e),
                code = (e as? FirebaseAuthException)?.errorCode
            )
        }
    }

    suspend fun register(email: String, password: String): ApiResult<Unit> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            ApiResult.Success(Unit)
        } catch (e: Exception) {
            ApiResult.Error(
                message = mapFirebaseError(e),
                code = (e as? FirebaseAuthException)?.errorCode
            )
        }
    }

    private fun mapFirebaseError(e: Exception): String = when ((e as? FirebaseAuthException)?.errorCode) {
        "ERROR_INVALID_EMAIL" -> "Email không đúng định dạng"
        "ERROR_USER_NOT_FOUND" -> "Email chưa đăng ký"
        "ERROR_WRONG_PASSWORD" -> "Sai mật khẩu"
        "ERROR_WEAK_PASSWORD" -> "Mật khẩu quá yếu (tối thiểu 6 ký tự)"
        "ERROR_NETWORK_REQUEST_FAILED" -> "Lỗi kết nối mạng"
        else -> "Lỗi: ${e.message ?: "Không xác định"}"
    }

    fun logout() = auth.signOut()
}