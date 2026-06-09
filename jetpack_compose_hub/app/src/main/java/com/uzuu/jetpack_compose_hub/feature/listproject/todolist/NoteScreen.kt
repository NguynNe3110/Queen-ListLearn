package com.uzuu.jetpack_compose_hub.feature.listproject.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
    onClickFAB: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    noteScreenContent(
        state = uiState,
        onClickFAB = onClickFAB
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
                    Text("TODO LIST")
                },
                actions = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.ic_search_gr),
                        contentDescription = "search"
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(

            ) {

            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { onClickFAB() },

            ) {

            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.items) {item->
                itemList(item)
            }
        }
    }
}

@Composable
fun itemList(note: Note) {

    var checked by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = false,
            onCheckedChange = {checked = it}
        )
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .height(24.dp),

        ) {
            Text("{${note.content}",)
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