package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.feature.columnQueen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColumnActivityQ: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Collect state từ ViewModel
                    val viewModel: ColumnViewModel = viewModel()
                    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

                    ColumnScreen(
                        uiState = uiState.value,
                        onItemClicked = { item ->
                            // Xử lý click tại đây
                            println("Clicked: ${item.title}")
                        }
                    )
                }
            }
        }
    }
}