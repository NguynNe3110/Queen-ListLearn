package com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.feature.rowQueen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.uzuu.jetpack_compose_hub.ui.theme.JetpackComposeHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RowQActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RowQScreen(
                        onNavigateToDetail = { itemId ->
                            // TODO: Navigate to detail screen
                            Toast.makeText(this, "Navigate to: $itemId", Toast.LENGTH_SHORT).show()
                        },
                        onShowSnackbar = { message ->
                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}