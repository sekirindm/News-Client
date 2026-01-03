package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.ui.R

@Composable
fun CircleAsyncImage(
    url: String?,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier) {
        val size = minOf(maxWidth, maxHeight)

        if (url.isNullOrBlank()) {
            Image(
                painter = painterResource(R.drawable.ic_terminal),
                contentDescription = null,
                modifier = Modifier
                    .size(size)
            )
        } else {
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier
                    .size(size)
            )
        }
    }
}



@Preview
@Composable
internal fun CircleAsyncImagePreview() {
    CircleAsyncImage(url = null)
}