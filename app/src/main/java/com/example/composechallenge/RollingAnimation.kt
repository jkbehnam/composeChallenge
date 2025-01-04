package com.example.composechallenge

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
private fun Preview50(){
    RightEdgeRollingCard()
}
@Composable
fun RightEdgeRollingCard() {
    var startAnimation by remember { mutableStateOf(false) }

    // Animating rotation along the Y-axis
    val rotationYy by animateFloatAsState(
        targetValue = if (startAnimation) 360f else 0f,
        animationSpec = tween(durationMillis = 1200)
    )

    // Animating horizontal translation
    val translationXx by animateFloatAsState(
        targetValue = if (startAnimation) -300f else 0f,
        animationSpec = tween(durationMillis = 1200)
    )

    // Animating scale for rolling effect
    val scaleXx by animateFloatAsState(
        targetValue = if (startAnimation) 0f else 1f,
        animationSpec = tween(durationMillis = 1200)
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(200.dp, 300.dp)
                .graphicsLayer {
                    rotationY = rotationYy
                    translationX = translationXx
                    scaleX = scaleXx
                    cameraDistance = 8 * density
                }
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxSize(),

            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "Rolling Card", fontSize = 24.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to trigger animation
        Button(onClick = { startAnimation = !startAnimation }) {
            Text(text = "Roll from Right Edge")
        }
    }
}