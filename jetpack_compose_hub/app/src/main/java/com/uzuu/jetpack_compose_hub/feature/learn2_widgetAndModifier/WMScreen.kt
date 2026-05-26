package com.uzuu.jetpack_compose_hub.feature.learn2_widgetAndModifier

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.uzuu.jetpack_compose_hub.R

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
                    ToastFun(context, "FAB action")
                }// todosomthing
            ) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())// luôn đặt trước padding,
                .padding(1.dp),//  quy tắc cái nào viết trước thì là cha
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

            ButtonTextModifier(nameRed = sharedTextRed, nameGreen = sharedTextGreen)

            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxWidth()
                    .padding(PaddingValues(16.dp, 0.dp)),
                shadowElevation = 4.dp
            ) {
                Column(
                    horizontalAlignment =  Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp)
                ) {
                    InputTextField(
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

            ImageAsyncImage()
            Icon()
            Modifier()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    Lesson2Screen()
}

fun ToastFun (context: Context, message: String) {
    Toast.makeText(context, "FAB action", Toast.LENGTH_SHORT).show()
}

@Composable
fun Title(name: String, space: Dp = 0.dp) { // có thể gán giá trị mặc định
    Text("🔹 $name", style = MaterialTheme.typography.titleMedium)
    Spacer(modifier = Modifier.height(space))
}

@Composable
fun ButtonTextModifier(nameRed: String, nameGreen: String) {
    val context = LocalContext.current
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
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Title("Button")

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Button( // không nên set background = modifier
                        onClick = {
                            ToastFun(context = context, "Save button")
                        },
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
                        onClick = {
                            ToastFun(context, "Delete action")
                        },
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
                        onClick = {
                            ToastFun(context, "FilledTonalButton action")
                        },
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
                        onClick = {
                            ToastFun(context, "IconButton action")
                        },

                        ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null
                        )
                    }
                }
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(12.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00FFAB),
                        contentColor = Color.DarkGray
                    ),

                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp
                    ),

                    border = BorderStroke(
                        1.dp,
                        Color.Black.copy(alpha = 0.2f)
                    )
                ) {
                    Text("Save")
                }
            }
        }
    }
}

@Composable
fun InputTextField(
    textRed: String,
    textGreen: String,
    onTextRedChange: (String) -> Unit,
    onTextGreenChange: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Title("InputField")

        TextField(
            value = textRed,
            onValueChange = onTextRedChange,
            label = {
                Text("Text Red")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))
        Title("OutlinedTextField")

        OutlinedTextField(
            value = textGreen,
            onValueChange = onTextGreenChange,
            label = {
                Text("Text Green")
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ImageAsyncImage() {
    val context = LocalContext.current
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp),
        shadowElevation = 4.dp

    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(16.dp),

        ) {
            Title("Image")
            Image(
                painter = painterResource(id = R.drawable.cloud),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier.clickable{
                    ToastFun(context, "Image action")
                }
            )
            Spacer(modifier = Modifier.height(4.dp) )

            Title("AsyncImage")
            AsyncImage(
                model = "https://i.pinimg.com/736x/67/d7/a1/67d7a156636fbc09bb789b0e9b662e83.jpg",
                contentDescription = null,
                modifier = Modifier.clickable {
                    ToastFun(context, "AsyncImage action")
                }
            )
        }
    }
}

@Preview(
    name = "Phần dưới",
    showBackground = true
)
@Composable
fun PreviewSecond() {
    second()
}

@Composable
fun second() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp),
    ) {
        Icon()
        Modifier()
    }
}
@Composable
fun Icon(){
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),

            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null
                )
                Icon(
                    imageVector = Icons.Default.AddCard,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun Modifier() {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Title("MODIFIER")

            Text("Text thươờng",
                modifier = Modifier.background(Color.Red))
            Text("Text với padding = 16",
                modifier = Modifier.background(Color.Red)
                    .padding(16.dp))
            Text("Text set size = 100",
                modifier = Modifier.background(Color.Red)
                    .size(100.dp))
            Text("Text fillmaxwidth",
                modifier = Modifier.background(Color.Red)
                    .fillMaxWidth()) //Chiếm toàn bộ chiều ngang.
            Text("Text fillmaxsize = occupid full screen")
//                modifier = Modifier.background(Color.Red)
//                    .fillMaxSize()) //Chiếm toàn màn hình.
            click()
        }
    }
}

@Composable
fun click() {
    val ct = LocalContext.current
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 4.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),

        ) {
            Text("Text", modifier = Modifier.clickable{
                ToastFun(ct, "Text click modifier")
                }
            )
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null,
                modifier = Modifier.combinedClickable(
                    onClick = {
                        ToastFun(ct, "Text click modifier combinedClickable :onClick")

                    },
                    onLongClick = {
                        ToastFun(ct, "Text click modifier combinedClickable : onLongClick")

                    },
                    onDoubleClick = {
                        ToastFun(ct, "Text click modifier combinedClickable : onDoubleClick")

                    },
//                    onClickLabel = { }, cho trình đọc màn hình
//                    onLongClickLabel = { }  cho trình đọc màn hình
                )
            )
        }
    }
}