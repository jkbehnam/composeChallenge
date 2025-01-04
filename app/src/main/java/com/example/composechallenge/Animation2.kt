package com.example.composechallenge

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Preview
@Composable
fun AnimationShowcase() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 1. State-based Color Animation
        StateBasedColorAnimation()

        // 2. Infinite Transition Animation
        InfiniteTransitionAnimation()

        // 3. Size Animation
        SizeAnimation()

        // 4. Animated Visibility
        AnimatedVisibilityExample2()

        // 5. Crossfade Animation
        CrossfadeAnimationExample()

        // 6. Animated Content
        AnimatedContentExample3()
    }
}

@Composable
fun StateBasedColorAnimation() {
    var isSelected by remember { mutableStateOf(false) }

    // Animate color based on state
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Color.Red else Color.Blue,
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(backgroundColor)
            .clickable { isSelected = !isSelected }
    )
}

@Composable
fun InfiniteTransitionAnimation() {
    val infiniteTransition = rememberInfiniteTransition()

    // Infinite color animation
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Infinite scale animation
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(color)
    )
}

@Composable
fun SizeAnimation() {
    var isExpanded by remember { mutableStateOf(false) }

    // Animate size using animateDpAsState
    val size by animateDpAsState(
        targetValue = if (isExpanded) 200.dp else 100.dp,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        modifier = Modifier
            .size(size)
            .background(Color.Green)
            .clickable { isExpanded = !isExpanded }
    )
}

@Composable
fun AnimatedVisibilityExample2() {
    var visible by remember { mutableStateOf(false) }

    Column {
        Button(onClick = { visible = !visible }) {
            Text("Toggle Visibility")
        }

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.Magenta)
            )
        }
    }
}

@Composable
fun CrossfadeAnimationExample() {
    var currentPage by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { currentPage = (currentPage + 1) % 3 }) {
            Text("Switch Page")
        }

        Crossfade(
            targetState = currentPage,
            animationSpec = tween(durationMillis = 500)
        ) { page ->
            when (page) {
                0 -> Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.Red)
                )
                1 -> Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.Green)
                )
                2 -> Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.Blue)
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentExample3() {
    var count by remember { mutableStateOf(0) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { count++ }) {
            Text("Increment")
        }

        AnimatedContent(
            targetState = count,
            transitionSpec = {
                fadeIn() + expandVertically() with
                        fadeOut() + shrinkVertically()
            }
        ) { targetCount ->
            Text(
                text = "Count: $targetCount",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}