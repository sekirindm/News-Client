package com.example.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.ui.R
import okhttp3.OkHttpClient

@Composable
fun ArticleImage(
    url: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier.clip(RoundedCornerShape(15.dp)),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.ic_terminal),
        error = painterResource(R.drawable.ic_terminal)
    )
}



@Preview
@Composable
internal fun CircleAsyncImagePreview() {
    ArticleImage(url = null)
}