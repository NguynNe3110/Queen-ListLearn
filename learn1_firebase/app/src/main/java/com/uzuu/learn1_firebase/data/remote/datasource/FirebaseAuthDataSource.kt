package com.uzuu.learn1_firebase.data.remote.datasource

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthDataSource @Inject constructor() {
    private val auth = FirebaseAuth.getInstance()

//    suspend fun login(email: String, password: String) : AuthResult =
//        auth.signInWithEmailAndPassword(email, password).await()

    suspend fun login(email: String, password: String): String? {
        return suspendCancellableCoroutine { continuation ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    // ✅ Thành công: trả về null (không có lỗi)
                    continuation.resume(null, null)
                }
                .addOnFailureListener { exception ->
                    // ❌ Thất bại: map exception thành message tiếng Việt
                    val errorMessage = mapFirebaseError(exception)
                    continuation.resume(errorMessage, null)
                }
        }
    }

    suspend fun register(email: String, password: String): String? {
        return suspendCancellableCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    // ✅ Thành công: trả về null (không có lỗi)
                    continuation.resume(null, null)
                }
                .addOnFailureListener { exception ->
                    // ❌ Thất bại: map exception thành message tiếng Việt
                    val errorMessage = mapFirebaseError(exception)
                    continuation.resume(errorMessage, null)
                }
        }
    }

    /**
     * Map Firebase errorCode → message tiếng Việt
     * Chỉ check errorCode, KHÔNG parse message string!
     */
    private fun mapFirebaseError(e: Exception): String {
        val errorCode = (e as? FirebaseAuthException)?.errorCode

        return when (errorCode) {
            "ERROR_EMAIL_ALREADY_IN_USE" -> "Email này đã được đăng ký"
            "ERROR_INVALID_EMAIL" -> "Email không đúng định dạng"
            "ERROR_WEAK_PASSWORD" -> "Mật khẩu quá yếu (tối thiểu 6 ký tự)"
            "ERROR_NETWORK_REQUEST_FAILED" -> "Lỗi kết nối mạng, vui lòng thử lại"
            "ERROR_OPERATION_NOT_ALLOWED" -> "Chức năng chưa được bật trong Firebase Console"
            "ERROR_TOO_MANY_REQUESTS" -> "Quá nhiều yêu cầu, vui lòng thử lại sau"
            else -> {
                // Log lỗi lạ để debug sau này
                android.util.Log.e("FirebaseAuth", "Unknown error: ${e.message}", e)
                "Đăng ký thất bại, vui lòng thử lại"
            }
        }
    }
    fun logout() = auth.signOut()
}
