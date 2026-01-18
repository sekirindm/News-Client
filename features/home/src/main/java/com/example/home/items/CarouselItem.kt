package com.example.home.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.home.model.CarouselItemUiModel
import com.example.ui.theme.AppTheme
import com.example.ui.theme.colors.AppColorSchema
import com.example.ui.theme.colors.LocalAppColors
import com.example.ui.theme.spacing.LocalAppSpacing
import com.example.ui.theme.typography.LocalAppTypography

@Composable
fun CarouselItem(uiModel: CarouselItemUiModel, onClick: (Long) -> Unit, modifier: Modifier = Modifier) {
    val space = LocalAppSpacing.current
    val color = LocalAppColors.current
    val typography = LocalAppTypography.current

    Card(
        modifier = modifier
            .height(180.dp)
            .clickable { onClick(uiModel.id) },
        shape = RoundedCornerShape(20.dp)
    ) {
        Box() {
            AsyncImage(
                model = uiModel.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(color = color.contentStroke),
                error = ColorPainter(color = color.contentStroke)
            )

            Column {
                Text(
                    text = uiModel.sourceName,
                    modifier = Modifier
                        .padding(top = space.space10, start = space.space16)
                        .clip(RoundedCornerShape(15.dp))
                        .background(AppColorSchema.accentColor)
                        .padding(horizontal = space.space10, vertical = space.space4),
                    color = color.accentText,
                    style = typography.textSmall
                )

                Spacer(modifier = Modifier.weight(1f))

                Row {
                    Text(
                        text = uiModel.authorName ?: "Неизвестен",
                        modifier = Modifier
                            .padding(start = space.space16)
//                            .alpha(alpha)
                        ,
                        color = color.accentText,
                        style = typography.textSmall
                    )

                    Box(
                        Modifier
                            .padding(horizontal = space.space6)
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(color = color.contentBg)
                            .align(Alignment.CenterVertically),
                    )

                    Text(
                        text = uiModel.date,
//                        modifier = Modifier.alpha(alpha),
                        color = color.accentText,
                        style = typography.textSmall
                    )
                }

                Text(
                    text = uiModel.title,
                    modifier = Modifier
                        .padding(
                            start = space.space16,
                            bottom = space.space10,
                            top = space.space4,
                            end = space.space16
                        )
//                        .alpha(alpha)
                    ,
                    color = color.accentText,
                    style = typography.textMedium
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = false, showSystemUi = false)
fun CarouselItemPreview() {
    AppTheme {
        CarouselItem(
            CarouselItemUiModel(
                id = 0,
                imageUrl = null,
                sourceName = "Gizmodo",
                authorName = "Kyle Torpey",
                title = "Bitcoin crashes againBitcoin crashes againBitcoin crashes againBitcoin crashes againBitcoin crashes again",
                content = "Crypto market is in panic",
                date = "2024-12-2"
            ), {}
        )
    }
}