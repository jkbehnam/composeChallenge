package com.example.composechallenge

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.text.BreakIterator
import java.text.StringCharacterIterator

@Composable
fun AnimatedVisibilityExample() {
    var visible by remember { mutableStateOf(true) }
    Column {
        Button(onClick = { visible = !visible }) {
            Text("Toggle Visibility")
        }
        AnimatedVisibility(visible = visible) {
            Box(
                Modifier
                    .size(100.dp)
                    .background(Color.Red)
            )
        }
    }
}

@Composable
fun AnimateAsStateExample() {
    var expanded by remember { mutableStateOf(false) }
    val size by animateDpAsState(targetValue = if (expanded) 200.dp else 100.dp)

    Box(
        Modifier
            .size(size)
            .background(Color.Blue)
            .clickable { expanded = !expanded }
    )
}

@Composable
fun AnimatableExample() {
    val offset = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        offset.animateTo(300f, animationSpec = tween(5000))

        /*spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessVeryLow
        )*/
    }
    Box(
        Modifier
            .offset(x = offset.value.dp)
            .size(50.dp)
            .background(Color.Green)
    )
}

@Composable
fun UpdateTransitionExample() {
    var expanded by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = expanded, label = "")
    val size by transition.animateDp(label = "") { if (it) 200.dp else 100.dp }
    val color by transition.animateColor(label = "") { if (it) Color.Magenta else Color.Cyan }

    Box(
        Modifier
            .size(size)
            .background(color)
            .clickable { expanded = !expanded }
    )
}

@Composable
fun AnimatedContentExample() {
    var count by remember { mutableStateOf(0) }
    Column {
        Button(onClick = { count++ }) {
            Text("Increment")
        }
        AnimatedContent(targetState = count) { targetCount ->
            Text("Count: $targetCount")
        }
    }
}

@Composable
fun InfiniteTransitionExample() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    Box(
        Modifier
            .size(100.dp)
            .background(color)
    )
}

@Composable
fun PhysicsBasedAnimationExample() {
    var expanded by remember { mutableStateOf(false) }
    val size by animateDpAsState(
        targetValue = if (expanded) 300.dp else 150.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)
    )

    Box(
        Modifier
            .size(size)
            .background(Color.Yellow)
            .clickable { expanded = !expanded }
    )
}

@SuppressLint("RememberReturnType")
@Composable
fun AnimatedText() {
    val text = "This text animates as though it is being typed \uD83E\uDDDE\u200D♀\uFE0F \uD83D\uDD10  \uD83D\uDC69\u200D❤\uFE0F\u200D\uD83D\uDC68 \uD83D\uDC74\uD83C\uDFFD"
    val breakIterator = remember(text) { BreakIterator.getCharacterInstance() }
    var subString by remember { mutableStateOf("") }


    LaunchedEffect(text) {

        breakIterator.text = StringCharacterIterator(text)
        var nextIndex = breakIterator.next()

        while (nextIndex != BreakIterator.DONE) {

            subString = text.subSequence(0, nextIndex).toString()
            nextIndex = breakIterator.next()

            delay(50L)


        }

    }

    Text(subString)

}


@Composable
@Preview
 fun Preview15() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState(

                )
            )
    ) {
        AnimatedText()
        AnimatedVisibilityExample()
        AnimateAsStateExample()
        AnimatableExample()
        UpdateTransitionExample()
        AnimatedContentExample()
        InfiniteTransitionExample()
        PhysicsBasedAnimationExample()
    }

}
@Composable
fun testfinal(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState(

                )
            )
    ) {
        AnimatedText()
        AnimatedVisibilityExample()
        AnimateAsStateExample()
        AnimatableExample()
        UpdateTransitionExample()
        AnimatedContentExample()
        InfiniteTransitionExample()
        PhysicsBasedAnimationExample()
    }
}