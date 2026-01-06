package com.example.home.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.home.items.NewsItem
import com.example.home.model.NewsItemUiModel
import com.example.ui.theme.AppTheme
import com.example.ui.theme.spacing.LocalAppSpacing

@Composable
fun NewsListSection(listNews: List<NewsItemUiModel>, onItemClick: (Long) -> Unit) {
    val spacer = LocalAppSpacing.current
    Column() {
        listNews.forEach { news ->
            NewsItem(uiModel =  news, onClick = onItemClick)
            Spacer(Modifier.padding(bottom = spacer.space10))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewsListSectionPreview() {
    val items = listOf(
        NewsItemUiModel(
            id = 0,
            imageUrl = "https://static.foxnews.com/foxnews.com/content/uploads/2025/04/3-is-it-safe-to-unsubscribe-from-spam-you-didnt-sign-up-for.jpg",
            sourceName = "BBC News",
            authorName = "John Doe",
            title = "Bitcoin Continues Steep Decline",
            content = "Bitcoin’s price keeps falling amid market uncertainty.",
            date = "21 Nov 2025"
        ),
        NewsItemUiModel(
            id = 1,
            imageUrl = "https://techcrunch.com/wp-content/uploads/2019/10/Jeff-Bezos-IAC-2019.jpg",
            sourceName = "The Verge",
            authorName = "Jane Smith",
            title = "AI Is Reshaping Mobile Development",
            content = "Developers are increasingly adopting AI tools.",
            date = "20 Nov 2025"
        ),
        NewsItemUiModel(
            id = 2,
            imageUrl = null,
            sourceName = "Reuters",
            authorName = "Reuters Staff",
            title = "Global Markets React to Fed Decision",
            content = "Stocks fluctuated after the latest Fed announcement.",
            date = "19 Nov 2025"
        )
    )
    AppTheme {
        NewsListSection(items, {})
    }
    /*NewsItemUiModel(
        id = 0,
        imageUrl = "https://mkiska.name/photos/3484/15.jpg",
        sourceName = "BBC News",
        authorName = "John Doe",
        title = "Bitcoin Continues Steep Decline",
        content = "Bitcoin’s price keeps falling amid market uncertainty.",
        date = "21 Nov 2025"
    ),
    NewsItemUiModel(
        id = 1,
        imageUrl = "https://mkiska.name/photos/3451/6.jpg",
        sourceName = "The Verge",
        authorName = "Jane Smith",
        title = "AI Is Reshaping Mobile Development",
        content = "Developers are increasingly adopting AI tools.",
        date = "20 Nov 2025"
    ),
    NewsItemUiModel(
        id = 2,
        imageUrl = null,
        sourceName = "Reuters",
        authorName = "Reuters Staff",
        title = "Global Markets React to Fed Decision",
        content = "Stocks fluctuated after the latest Fed announcement.",
        date = "19 Nov 2025"
    )*/
}