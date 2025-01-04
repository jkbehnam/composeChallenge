package com.example.composechallenge

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
private fun Preview51(){
    LabelPeelingAnimation()
}

@Composable
fun LabelPeelingAnimation() {
    var startPeeling by remember { mutableStateOf(false) }

    // Animate rotation around the X-axis
    val rotationXx by animateFloatAsState(
        targetValue = if (startPeeling) -90f else 0f, // Peel downwards
        animationSpec = tween(durationMillis = 1000)
    )

    // Animate scaling for peeling effect
    val scaleYy by animateFloatAsState(
        targetValue = if (startPeeling) 0f else 1f, // Shrink the height
        animationSpec = tween(durationMillis = 1000)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp, 100.dp) // Label size
                .graphicsLayer {
                    rotationX = rotationXx // Apply peeling rotation
                    scaleY = scaleYy      // Shrink height to simulate peeling
                    cameraDistance = 8 * density // Add depth
                    transformOrigin = androidx.compose.ui.graphics.TransformOrigin(1f, 0f) // Pivot from top-right corner
                }
                .background(Color.Blue, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Label", color = Color.White, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { startPeeling = !startPeeling }) {
            Text(text = if (startPeeling) "Reset" else "Peel Off")
        }
    }
}