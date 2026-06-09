package com.uzuu.jetpack_compose_hub.feature.listproject.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uzuu.jetpack_compose_hub.R
import com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn2_widgetAndModifier.first.Modifier
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun NoteScreen(
    viewModel: NoteViewModel = hiltViewModel(),
    onSearch: () -> Unit,
    onClickItem: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiStateDialog by viewModel.uiStateDialog.collectAsStateWithLifecycle()

    noteScreenContent(
        state = uiState,
        onClickFAB = viewModel::DialogShow,

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun noteScreenContent(
    state: NoteUiState,
    onClickFAB: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "TODO LIST",
                        color = Color(0xFF718156)
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            onClickFAB()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_search_gr),
                            contentDescription = "search",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
//        bottomBar = {
//            BottomAppBar(
//
//            ) {
//
//            }
//        },
        floatingActionButton = {
            // mặc định
            FloatingActionButton(
                onClick = { onClickFAB() },
//                modifier = Modifier.background(Color(0xFF3B432D)),
                containerColor = Color(0xFF8C9D6C)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add_fab),
                    contentDescription = "Cart",
                    modifier = Modifier.size(12.dp),
                    tint = Color.Unspecified
                )
            }

//  Đẳng cấp hơn
//            ExtendedFloatingActionButton(
//                onClick = onNavigateToPost,
//                modifier = Modifier.height(40.dp),
//                icon = {
//                    Icon(
//                        painter = painterResource(R.drawable.ic_add_fab),
//                        contentDescription = "Cart",
//                        modifier = Modifier.size(12.dp),
//                        tint = Color.Unspecified
//                    )
//                },
//                text = { Text("Đăng bài") },
//                containerColor = AppColor.Secondary.copy(alpha = 0.8f),
//                contentColor = Color.White,
//                elevation = FloatingActionButtonDefaults.elevation(
//                    defaultElevation = 6.dp
//                )
//            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(start = 8.dp, end = 12.dp)
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                state.items,
//                key= { it = id } // hỏi thêm
            ) {item->
                itemList(item)
            }
        }
    }
}

@Composable
fun itemList(note: Note) {

    var checked by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {checked = it}
        )
        Surface(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .combinedClickable(
                    onClick = {
                        //todo code navagate
                    },
                    onLongClick = {
                        //todo open dialog
                    }
                ),
//                .clickable {
//                    // todo code
//
//                },
            shadowElevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                "${note.content}",
                modifier = Modifier.padding(16.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}




@Preview
@Composable
fun preItemList() {
    itemList(
        Note(
            0,
            "nguyen",
            false
        )
    )
}

@Preview
@Composable
fun PreviewScreen() {
    noteScreenContent(
        state = NoteUiState(
            true,
            notes
        ),
        onClickFAB = {}
    )
}

val notes = listOf(
    Note(1, "Mua sữa tươi", isDone = false),
    Note(2, "Nộp báo cáo tuần", isDone = true),
    Note(3, "Gọi điện cho mẹ", isDone = false),
    Note(4, "Học Kotlin cơ bản", isDone = true),
    Note(5, "Tập thể dục buổi sáng", isDone = false),
    Note(6, "Viết email cho khách hàng", isDone = true),
    Note(7, "Đặt vé máy bay", isDone = false),
    Note(8, "Đọc 10 trang sách", isDone = false),
    Note(9, "Họp team lúc 14h", isDone = true),
    Note(10, "Sửa lỗi bug #123", isDone = false)
)