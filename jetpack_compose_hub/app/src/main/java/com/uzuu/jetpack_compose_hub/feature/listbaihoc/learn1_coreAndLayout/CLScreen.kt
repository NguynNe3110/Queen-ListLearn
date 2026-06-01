package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn1_coreAndLayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uzuu.jetpack_compose_hub.R

@Composable
fun CLScreen() {
//    val context = LocalContext.current
//    var count: Int = 0

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Hello compose")
        Button(
            onClick = {
//                count++
//                Toast.makeText(context, "Click count: ${count}", Toast.LENGTH_SHORT).show()
//                if(count == 10) count = 0
            }
        ) {
            Text("Click me!")
        }

        HorizontalScreen()

        update()

        boxLayout()
    }
}

@Composable
fun boxLayout() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )

        // item nằm phía treen
        Text(
            "Badge",
            color = Color.Red,
            modifier = Modifier
                .align(Alignment.TopEnd) // góc trên phải
                .padding(8.dp)
        )
    }
}

@Composable
fun HorizontalScreen() {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, // căn giữa theo chiều dọc
            horizontalArrangement = Arrangement.spacedBy(8.dp) // khoảng cách giữa các phần tử
        ){
            Text("A")
//            Spacer(modifier = Modifier.width(43.dp))
            Text("B")
//            Spacer(modifier = Modifier.width(12.dp))
            Text("C")
        }

        Spacer(modifier = Modifier.width(43.dp))

        Box (
            modifier = Modifier.fillMaxWidth()
                .padding(12.dp)
        ) {
            Surface(
                color = Color.Blue
            ) {
                Text("Background         ")
            }

            Button(
                onClick = {}
            ) {
                Text("Button")
            }

            Surface(
                color = Color.Red
            ) {
                Text("nngueen")
            }
        }
    }
}

@Composable
fun update() {
    Surface (
        color = Color.LightGray
    ) {
        Text("Hello")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCLScreen() {
    CLScreen()
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Learn1_coreAndLayout")
                }
            )
        },

        bottomBar = {
            BottomAppBar {
                Text("Bottom mune")
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
            ) {
                Text("ng")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Text("Hello Scaffold")

        }

        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {

            CLScreen()

            Lesson1Screen()
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lesson1Screen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("📐 Bài 1: Core Layout") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Column demo
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 4.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("🔹 Column: xếp dọc", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Item 1")
                    Text("Item 2")
                    Text("Item 3")
                }
            }

            // 2. Row demo
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("🔹 Row: xếp ngang")
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = { }) { Text("Bấm") }
                }
            }

            // 3. Box demo
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant, // backgound
                shape = MaterialTheme.shapes.medium, // bo góc mặc định
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp) //
            ) {
                Box {
                    Text(
                        "🔹 Box: xếp chồng",
                        modifier = Modifier
                            .align(Alignment.Center) //
                    )
                    Text(
                        "Badge",
                        modifier = Modifier
                            .align(Alignment.TopEnd) //
                            .padding(8.dp)
                            .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}