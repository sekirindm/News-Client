package com.example.home.sections

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.home.items.CarouselItem
import com.example.home.model.CarouselItemUiModel
import kotlinx.coroutines.delay
import kotlin.math.abs

@SuppressLint("UnusedBoxWithConstraintsScope")
@Suppress("COMPOSE_APPLIER_CALL_MISMATCH")
@Composable
fun CarouselSection(
    items: LazyPagingItems<CarouselItemUiModel>,
    intervalMs: Long = 4000L
) {
    val listState = rememberLazyListState()
    var activeIndex by remember { mutableIntStateOf(0) }

    BoxWithConstraints {
        val horizontalPadding = 24.dp
        val peekWidth = 6.dp
        val itemSpacing = 8.dp

        val itemWidth = maxWidth - horizontalPadding * 2 - peekWidth * 2

        LaunchedEffect(items.itemCount) {
            if (items.itemCount == 0) return@LaunchedEffect

            while (true) {
                delay(intervalMs)

                if (!listState.isScrollInProgress && items.itemCount > 0) {
                    val next = (activeIndex + 1) % items.itemCount
                    activeIndex = next
                    listState.slowScrollToItem(
                        targetIndex = next,
                        durationMs = 900
                    )
                }
            }
        }

        LaunchedEffect(listState) {
            snapshotFlow { listState.layoutInfo.visibleItemsInfo }
                .collect { visibleItems ->
                    if (visibleItems.isNotEmpty()) {
                        val viewportCenter =
                            (listState.layoutInfo.viewportStartOffset +
                                    listState.layoutInfo.viewportEndOffset) / 2

                        val nearest = visibleItems.minByOrNull {
                            abs(it.offset + it.size / 2 - viewportCenter)
                        }?.index

                        nearest?.let { activeIndex = it }
                    }
                }
        }

        val flingBehavior = rememberSnapFlingBehavior(
            lazyListState = listState,
            snapPosition = SnapPosition.Center
        )

        LazyRow(
            state = listState,
            contentPadding = PaddingValues(horizontal = horizontalPadding),
            horizontalArrangement = Arrangement.spacedBy(itemSpacing),
            flingBehavior = flingBehavior,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(
                count = items.itemCount,
                key = { index -> items[index]?.id ?: index }
            ) { index ->
                val item = items[index] ?: return@items

                CarouselItem(
                    uiModel = item,
                    onClick = {},
                    modifier = Modifier.width(itemWidth)
                )
            }
        }
    }
}


suspend fun LazyListState.slowScrollToItem(
    targetIndex: Int,
    durationMs: Int
) {
    val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
    val targetItem = layoutInfo.visibleItemsInfo.firstOrNull { it.index == targetIndex }

    if (targetItem != null) {
        val itemCenter = targetItem.offset + targetItem.size / 2
        val delta = (itemCenter - viewportCenter).toFloat()

        animateScrollBy(
            value = delta,
            animationSpec = tween(
                durationMillis = durationMs,
                easing = FastOutSlowInEasing
            )
        )
    } else animateScrollToItem(targetIndex)
}