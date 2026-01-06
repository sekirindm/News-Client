package com.example.home.items

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.home.model.NewsItemUiModel
import com.example.ui.components.ArticleImage
import com.example.ui.theme.AppTheme
import com.example.ui.theme.colors.LocalAppColors
import com.example.ui.theme.spacing.LocalAppSpacing
import com.example.ui.theme.typography.LocalAppTypography

@Composable
fun NewsItem(uiModel: NewsItemUiModel, onClick: (Long) -> Unit) {
    val color = LocalAppColors.current
    val typography = LocalAppTypography.current
    val space = LocalAppSpacing.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(116.dp)
            .clickable(enabled = true, onClick = { onClick(uiModel.id) }),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = color.contentBg, contentColor = color.contentText)
    ) {
        Row {
            Log.d("LOGGGGGGGGG", "${uiModel.imageUrl}")
            ArticleImage(uiModel.imageUrl, modifier = Modifier.size(116.dp))

            Column(modifier = Modifier
                .padding(start = space.space10, top = space.space4)
                .fillMaxWidth()) {
                Text(uiModel.sourceName, style = typography.textSmall, color = color.contentStroke)
                Text(uiModel.title, style = typography.textLargeMedium, color = color.contentText)

                Spacer(modifier = Modifier.weight(1f))

                Row(modifier = Modifier.padding(bottom = space.space2)) {
                    Text(uiModel.authorName?: "Неизвестен", style = typography.textSmall, color = color.contentStroke)
                    Text(uiModel.date, style = typography.textSmall, color = color.contentStroke)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = false)
fun NewsItemPreview() {
    AppTheme {
        NewsItem(
            NewsItemUiModel(
                id = 0,
                imageUrl = null,
                sourceName = "Gizmodo",
                authorName = "Kyle Torpey",
                title = "Bitcoin crashes againBitcoin crashes againBitcoin crashes againBitcoin crashes againBitcoin crashes again",
                content = "Crypto market is in panic",
                date = "2024-12-2"
            )
        ) {}
    }
}