package com.uzuu.jetpack_compose_hub.feature.ztest.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun ComplexSplashScreen(
    onSplashFinished: () -> Unit
) {
    var phase by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = true) {
        delay(700)
        phase = 1

        delay(1400)
        phase = 2

        delay(2100)
        phase = 3

        delay(2900)
        phase = 4

        delay(3300)
        onSplashFinished()
    }

    // Background Circle Scale
    val bgScale by animateFloatAsState(
        targetValue = if (phase >= 1) 15f else 0.1f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "bgScale"
    )

    // Icon Slide & Alpha
    val iconOffsetX by animateDpAsState(
        targetValue = when (phase) {
            2 -> (-80).dp
            3 -> 0.dp
            else -> 0.dp
        },
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing),
        label = "iconOffsetX"
    )

    val iconAlpha by animateFloatAsState(
        targetValue = if (phase in 2..3) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "iconAlpha"
    )

    // Text Logo Alpha
    val textLogoAlpha by animateFloatAsState(
        targetValue = if (phase == 2) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "textLogoAlpha"
    )

    // Slogan Slide Down (Đã thay OvershootEasing bằng FastOutSlowInEasing)
    val sloganOffsetY by animateDpAsState(
        targetValue = if (phase >= 4) 0.dp else (-60).dp,
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing),
        label = "sloganOffsetY"
    )

    val sloganAlpha by animateFloatAsState(
        targetValue = if (phase >= 4) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "sloganAlpha"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Background
        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(scaleX = bgScale, scaleY = bgScale)
                .clip(CircleShape)
                .background(Color(0xFFFF4A29))
        )

        // Content Group
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Áp dụng lambda overload cho Modifier.offset
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.offset { IntOffset(iconOffsetX.roundToPx(), 0) }
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .alpha(iconAlpha)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Uzuu",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.alpha(textLogoAlpha)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Áp dụng lambda overload cho Modifier.offset ở Y axis
            Text(
                text = "Learn & Grow",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 16.sp,
                modifier = Modifier
                    .offset { IntOffset(0, sloganOffsetY.roundToPx()) }
                    .alpha(sloganAlpha)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewComplexSplash() {
    MaterialTheme {
        Surface {
            ComplexSplashScreen(onSplashFinished = {})
        }
    }
}

class TryAnimation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    ComplexSplashScreen(onSplashFinished = {
                        // TODO: Navigate to Main Screen
                    })
                }
            }
        }
    }
}