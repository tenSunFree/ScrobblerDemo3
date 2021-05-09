package com.example.scrobblerdemo3

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.scrobblerdemo3.common.component.FullScreenLoadingComponent
import com.example.scrobblerdemo3.common.component.LoadingComponent
import com.example.scrobblerdemo3.common.utils.UIError
import com.example.scrobblerdemo3.home.viewModel.HomeViewModel
import com.example.scrobblerdemo3.home.model.HomeBaseEntity
import com.google.accompanist.coil.CoilImage

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    errorHandler: @Composable (UIError) -> Unit,
) {
    val state by viewModel.dataState.collectAsState()
    if (state.isError) {
        errorHandler(
            UIError.ShowErrorSnackbar(
                state = state,
                fallbackMessage = stringResource(id = R.string.error_charts),
                onAction = { viewModel.getData() }
            ))
    }
    LoadingComponent(
        empty = state.isInitialLoading,
        emptyContent = { FullScreenLoadingComponent() }
    ) {
        state.currentData?.let {
            HomeList(it)
        } ?: FullScreenErrorComponent()
    }
}

@Composable
private fun HomeList(
    chartData: List<HomeBaseEntity.HomeEntity>,
    modifier: Modifier = Modifier,
) = LazyColumn(modifier) {
    itemsIndexed(items = chartData) { _, entry ->
        HomeListItem(
            name = entry.title,
            url = entry.url,
            onClicked = { Log.d("more", "click") }
        )
        CustomDividerComponent()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeListItem(name: String, url: String, onClicked: () -> Unit) {
    ListItem(
        text = { Text(text = name, maxLines = 1, overflow = TextOverflow.Ellipsis) },
        icon = { HomeListItemIcon(url = url) },
        modifier = Modifier.clickable(onClick = { onClicked() }),
    )
}

@Composable
fun HomeListItemIcon(
    url: String
) {
    Surface(
        color = Color.Gray,
        shape = CircleShape,
        modifier = Modifier.size(40.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            CoilImage(
                data = url,
                contentDescription = "description of the image"
            )
        }
    }
}