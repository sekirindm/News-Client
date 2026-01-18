package com.example.home.sections

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.home.items.NewsItem
import com.example.home.model.NewsItemUiModel
import com.example.ui.theme.spacing.LocalAppSpacing

@Composable
fun NewsListSection(
    listNews: LazyPagingItems<NewsItemUiModel>,
    onItemClick: (Long) -> Unit
) {
    val spacer = LocalAppSpacing.current
    Log.d("LOGGGGGGGGG", "listNews $listNews")

    LazyColumn {
        items(
            count = listNews.itemCount,
            key = { index ->
                val key = listNews[index]?.id ?: index
                Log.d("LazyColumnKey", "Index $index -> Key $key")
                key
            }
        ) { index ->
            val news = listNews[index]?: run {
                Log.d("LazyColumnItem", "Index $index -> null placeholder")
                return@items
            }
            Log.d("LazyColumnItem", "Rendering item ${news.id} at index $index")

            NewsItem(
                uiModel = news,
                onClick = onItemClick
            )
            Spacer(Modifier.padding(spacer.space10))
        }
    }
}