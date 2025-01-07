package com.example.composechallenge

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
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

    @Composable
    fun CardPager() {
        val pagerState = rememberPagerState(pageCount = { colors.size })

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 80.dp),
            pageSpacing = 16.dp,
        ) { page ->
            Card(
                modifier = Modifier
                    .width(250.dp)
                    .height(300.dp)
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) +
                                        pagerState.currentPageOffsetFraction
                                ).absoluteValue

                        val scale = lerp(1f, 0.8f, pageOffset.coerceIn(0f, 1f))
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
                    text = "Behnam $page",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun BasicPager() {
    val pagerState = rememberPagerState(pageCount = { 5 })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.height(200.dp)
    ) { page ->
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("صفحه ${page + 1}")
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun EdgeVisiblePager() {
    val pagerState = rememberPagerState(pageCount = { 5 })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.height(200.dp),
        contentPadding = PaddingValues(horizontal = 32.dp),
        pageSpacing = 0.dp
    ) { page ->
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("صفحه ${page + 1}")
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun EdgeVisiblePager2() {
    val pagerState = rememberPagerState(pageCount = { 5 })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.height(200.dp),
        contentPadding = PaddingValues(horizontal = 32.dp),
        pageSpacing = 0.dp
    ) { page ->
        Card(

            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue
                    val scale = lerp(
                        start = 0.8f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    scaleX = scale
                    scaleY = scale

                    // Add some rotation for better effect
                    rotationZ = lerp(
                        start = 0f,
                        stop = -10f,
                        fraction = pageOffset.coerceIn(0f, 1f)
                    )
                }
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("صفحه ${page + 1}")
            }
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun ScaledEdgePager() {
    val pagerState = rememberPagerState(pageCount = { 5 })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.height(200.dp),
        contentPadding = PaddingValues(horizontal = 64.dp),
        pageSpacing = 16.dp,
        pageSize = PageSize.Fixed(240.dp)
    ) { page ->
        Card(
            modifier = Modifier
                .fillMaxHeight()
                .width(240.dp)
                .padding(8.dp)
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    val scale = lerp(
                        start = 0.8f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    scaleX = scale
                    scaleY = scale
                }
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("صفحه ${page + 1}")
            }
        }
    }
}

private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun AnimatedPager() {
    val pagerState = rememberPagerState(pageCount = { 5 })

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.height(200.dp),
            pageSize = PageSize.Fixed(300.dp)
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("صفحه ${page + 1}")
                }
            }
        }

        Row(
            Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { iteration ->
                val color = if (pagerState.currentPage == iteration)
                    MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(color)
                        .size(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun InfinitePager() {
    val virtualPageCount = Int.MAX_VALUE
    val actualPageCount = 5
    val pagerState = rememberPagerState(pageCount = { virtualPageCount })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.height(200.dp),
        contentPadding = PaddingValues(horizontal = 32.dp)
    ) { page ->
        val actualPage = page % actualPageCount
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("صفحه ${actualPage + 1}")
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun TabPager() {
    val tabs = listOf("خانه", "پروفایل", "تنظیمات", "درباره ما")
    val pagerState = rememberPagerState(pageCount = { tabs.size })

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = { /* Handle tab click */ }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.height(200.dp)
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = tabs[page],
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

private fun lerp2(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}
