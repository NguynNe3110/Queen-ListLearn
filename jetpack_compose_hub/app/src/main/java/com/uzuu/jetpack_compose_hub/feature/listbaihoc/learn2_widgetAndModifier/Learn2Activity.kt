package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn2_widgetAndModifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

class Learn2Activity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme{
                Surface {
                    Lesson2Screen()
                }
            }
        }
    }
}