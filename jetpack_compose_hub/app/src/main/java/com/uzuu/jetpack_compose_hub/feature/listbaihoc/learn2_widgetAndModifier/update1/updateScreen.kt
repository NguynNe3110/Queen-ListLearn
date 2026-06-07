package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn2_widgetAndModifier.update1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class updateScreen: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    ScreenUpdate()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenUpdate() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LazyRow + State + Event") },
                actions = {
                    //todo exam icon, button,...
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
                .fillMaxSize()
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        alignment = Alignment.CenterHorizontally)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp),
                        color = Color.Red,
                        strokeWidth = 5.dp,
                        trackColor = Color.Green
                    )
                    CircularProgressIndicator(
                        modifier = Modifier.size(70.dp),
                        color = Color.Red,
                        strokeWidth = 5.dp,
                        trackColor = Color.Green
                    )
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp),
                        color = Color.Red,
                        strokeWidth = 5.dp,
                        trackColor = Color.Green
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
fun Previe() {
    ScreenUpdate()
}

//Column(
//modifier = Modifier.verticalScroll(
//rememberScrollState()
//)
//) {
//    Text(...)
//    Button(...)
//    Image(...)
//    Text(...)
//}