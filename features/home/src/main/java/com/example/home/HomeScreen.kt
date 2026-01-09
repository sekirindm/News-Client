package com.example.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.home.sections.NewsListSection
import com.example.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(model: HomeViewModel = koinViewModel()) {

    LaunchedEffect(Unit) {
        model.dispatch(HomeViewModel.NewsViewAction.LoadNewsList)
    }

    val state by model.state.collectAsStateWithLifecycle()
    val items = state.items?.collectAsLazyPagingItems()?: return

    Column(modifier = Modifier.height(250.dp).padding(top = 16.dp, start = 10.dp, end = 10.dp)) {
        NewsListSection(listNews = items, onItemClick = { id ->
        })
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    AppTheme {

    }
}