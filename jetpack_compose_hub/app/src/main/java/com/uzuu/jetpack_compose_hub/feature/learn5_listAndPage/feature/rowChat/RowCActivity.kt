package com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.feature.rowChat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RowCActivity: ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    RowCNavHost()
                }
            }
        }
    }
}