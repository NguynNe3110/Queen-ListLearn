package com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.feature.columnChat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import androidx.compose.runtime.getValue

@Composable
fun UserScreen(
    viewModel: UserViewModel = hiltViewModel()
) {

    val uiState by viewModel
        .uiState
        .collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        items(
            items = uiState.users,
            key = { user ->
                user.id
            }
        ) { user ->

            UserItem(user)

        }
    }
}

@Composable
fun UserItem(
    user: User
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 6.dp
        )
    ) {

        Text(
            text = user.name,
            modifier = Modifier.padding(16.dp)
        )

    }
}