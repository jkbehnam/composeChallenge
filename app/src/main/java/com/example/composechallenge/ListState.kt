package com.example.composechallenge

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Random
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListState() {
    val colors = listOf(Color.White, Color.Red, Color.Green, Color.Yellow)
    val pagerState = rememberPagerState(pageCount = { 4 })
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // 5 seconds
            val nextPage = if (pagerState.currentPage == 0) colors.size - 1 else pagerState.currentPage - 1
            coroutineScope.launch {
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 40.dp),
        reverseLayout = false
    ) { page ->
        Card(
            modifier = Modifier
                .fillMaxWidth().height(300.dp)
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) +
                                    pagerState.currentPageOffsetFraction
                            ).absoluteValue
                    val scale = lerp(1f, 0.7f, pageOffset)
                    scaleX = scale
                    scaleY = scale
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
            colors = CardDefaults.cardColors(containerColor = colors[page])
        ) {
            Text(
                text = "Behnam ${page}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
@Preview
@Composable
private fun Preview(){
    ListState()
}

