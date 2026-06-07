package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.feature.columnChat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColumnActivityC : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    AppNavHost()
                }
            }
        }
    }
}