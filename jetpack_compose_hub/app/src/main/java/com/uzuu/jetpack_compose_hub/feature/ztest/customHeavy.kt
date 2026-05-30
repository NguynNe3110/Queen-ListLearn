package com.uzuu.jetpack_compose_hub.feature.ztest

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun OtpInputScreen() {
    var otpCode by remember { mutableStateOf(listOf("", "", "", "")) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("QUÊN MẬT KHẨU", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))

        Text("Mã xác nhận đã được gửi tới số điện thoại của bạn.")
        Spacer(Modifier.height(32.dp))

        // 1. Dãy các ô nhập liệu
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp) // Khoảng cách giữa các ô
        ) {
            repeat(4) { index ->
                OtpBox(
                    value = otpCode[index],
                    onValueChange = { newValue ->
                        val newOtp = otpCode.toMutableList()
                        newOtp[index] = newValue
                        otpCode = newOtp
                    },
                    isFocused = false // Có thể thêm logic focus nếu muốn
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        Button(onClick = { /* Verify OTP */ }) {
            Text("Xác nhận")
        }
    }
}

@Composable
fun OtpBox(value: String, onValueChange: (String) -> Unit, isFocused: Boolean) {
    Box(
        modifier = Modifier
            .size(50.dp) // Kích thước cố định cho ô
            .border(
                width = if (isFocused) 2.dp else 1.dp,
                color = if (isFocused) Color.Green else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        // TextField ẩn đi, chỉ hiện text lên Box để dễ custom
        BasicTextField(
            value = value,
            onValueChange = { if (it.length <= 1) onValueChange(it) },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            singleLine = true,
            decorationBox = { innerTextField ->
                innerTextField() // Hiển thị text ngay giữa Box
            }
        )
    }
}

@Preview
@Composable
fun PreviewOtpInputScreen(){OtpInputScreen()}