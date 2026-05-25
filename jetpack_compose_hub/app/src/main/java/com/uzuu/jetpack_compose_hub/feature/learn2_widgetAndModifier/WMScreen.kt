package com.uzuu.jetpack_compose_hub.feature.learn2_widgetAndModifier

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lesson2Screen() {
    var sharedTextRed by remember { mutableStateOf("") }
    var sharedTextGreen by remember { mutableStateOf("") }

    var context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Lesson 2: Widget & Modifier")
                }
            )
        },

        bottomBar = {
            BottomAppBar() {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Bottom menu")
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(context, "FAB action", Toast.LENGTH_SHORT).show()
                }// todosomthing
            ) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
                .fillMaxWidth()
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                shadowElevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.Gray)
                        .clickable { },//
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("🔹 Column: xếp dọc", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Item 1")
                    Text("Item 2")
                    Text("Item 3")
                }
            }

            Learn(nameRed = sharedTextRed, nameGreen = sharedTextGreen)

            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxWidth()
                    .padding(PaddingValues(16.dp, 0.dp)),
                shadowElevation = 4.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment =  Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Input(
                        textRed = sharedTextRed,
                        textGreen = sharedTextGreen,
                        onTextRedChange = { newTextRed ->
                            sharedTextRed = newTextRed
                        },
                        onTextGreenChange = { newTextGreen ->
                            sharedTextGreen = newTextGreen
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    Lesson2Screen()
}

@Composable
fun Learn(nameRed: String, nameGreen: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.fillMaxWidth(),
            shadowElevation = 4.dp
        ) {

            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),

            ) {
                Text(
                    text = "Hello $nameRed",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )

                Text(
                    text = "Hello $nameGreen    ",
                    style = MaterialTheme.typography.titleLarge, // nghieen cuu cai nafy
                    color = Color.Green
                )
            }
        }

        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.fillMaxWidth(),
            shadowElevation = 4.dp
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Button( // không nên set background = modifier
                    onClick = { },
                    shape = MaterialTheme.shapes.small,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),

                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp, // khi bình thường
                        pressedElevation = 8.dp, // đang nhấn xuoôống
                        focusedElevation = 6.dp, // được chọn qua bàn phím, TV
                        hoveredElevation = 6.dp, // di chuột qua
                        disabledElevation = 0.dp // nút bị khóa, k nhấn được
                    ),
                    contentPadding = PaddingValues(
                        horizontal = 24.dp,
                        vertical = 12.dp
                    )
                ) {
                    Text("Save")
                }

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(14.dp), // corner customer
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp, // khi bình thường
                        pressedElevation = 8.dp, // đang nhấn xuoôống
                        focusedElevation = 6.dp, // được chọn qua bàn phím, TV
                        hoveredElevation = 6.dp, // di chuột qua
                        disabledElevation = 0.dp // nút bị khóa, k nhấn được
                    ),
                    border = BorderStroke(
                        1.dp,
                        Color.Red
                    )
                ) {
                    Row {
//                        Icon()
                        Text("Delete")
                    }
                }

                FilledTonalButton(
                    onClick = {},
                    shape = MaterialTheme.shapes.small,
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp, // khi bình thường
                        pressedElevation = 8.dp, // đang nhấn xuoôống
                        focusedElevation = 6.dp, // được chọn qua bàn phím, TV
                        hoveredElevation = 6.dp, // di chuột qua
                        disabledElevation = 0.dp // nút bị khóa, k nhấn được
                    ),
                    //enabled = false -> để nhìn shadow của disableEleva
                ) {
                    Text("Edit")
                }

                IconButton(
                    onClick = {},

                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null
                    )
                }
            }
        }


    }
}

@Composable
fun Input(
    textRed: String,
    textGreen: String,
    onTextRedChange: (String) -> Unit,
    onTextGreenChange: (String) -> Unit
) {
    TextField(
        value = textRed,
        onValueChange = onTextRedChange,
        label = {
            Text("Text Red")
        }
    )

    OutlinedTextField(
        value = textGreen,
        onValueChange = onTextGreenChange,
        label = {
            Text("Text Green")
        },
        modifier = Modifier.fillMaxWidth()
    )
}