package com.example.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.home.sections.NewsListSection
import com.example.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(model: HomeViewModel = koinViewModel()) {
    val state by model.state.collectAsState()

    LaunchedEffect(Unit) {
        model.dispatch(HomeViewModel.NewsViewAction.LoadNewsList)
    }

    Column() {
        NewsListSection(listNews =  state.items, onItemClick = { id ->
            Log.d("LOGGGGGGGG", "id $id")
        })
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    AppTheme {

    }
}