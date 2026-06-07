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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uzuu.jetpack_compose_hub.R
import com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn2_widgetAndModifier.first.Modifier

@Composable
fun noteScreen() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun noteScreenContent(

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
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

        }
    }
}

@Composable
fun itemList(note: Note) {
    Row(
        modifier = Modifier.padding(12.dp)
            .fillMaxWidth(),
    ) {
        Checkbox(
            checked = false,
            )
        Surface(
            modifier = Modifier.padding(12.dp)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth() ,
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
        )
    )
}