package com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.feature.rowChat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun RowCScreen (
    navController: NavController,
    viewModel: RowCViewModel = hiltViewModel()
) {
    val uiState by viewModel
        .uiState
        .collectAsStateWithLifecycle()

    LazyRow(
        contentPadding = PaddingValues(
            horizontal = 16.dp
        ),

        horizontalArrangement =
            Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = uiState.products,
            key = { it.id }
        ) { product ->
            ProductItem(
                product = product,
                onClick = {
                    navController.navigate(
                        "detail/${product.name}"
                    )
                }
            )

        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .width(180.dp)
            .clickable {
                onClick()
            }
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = product.name
            )

        }
    }
}

