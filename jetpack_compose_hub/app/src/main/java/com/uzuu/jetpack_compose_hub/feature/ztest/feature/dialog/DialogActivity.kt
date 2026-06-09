package com.uzuu.jetpack_compose_hub.feature.ztest.feature.dialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.uzuu.jetpack_compose_hub.feature.listproject.todolist.NoteScreen
import dagger.hilt.android.AndroidEntryPoint

class DialogActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface{
                    DialogScreen()
                }
            }
        }
    }
}