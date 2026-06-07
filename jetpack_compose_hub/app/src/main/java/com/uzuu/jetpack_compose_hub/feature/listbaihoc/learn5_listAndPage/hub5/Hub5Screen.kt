package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.hub5

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HubScreen() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("🎓 Hub Học Android - Uzuu") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(LessonData.lessons) { lesson ->
                Card(
                    onClick = {
                        // Bấm vào card → mở Activity của bài đó
                        val intent = Intent(context, lesson.activityClass)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 6.dp
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(lesson.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(lesson.description, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}