package com.example.composechallenge

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import kotlinx.coroutines.launch

@Composable
@Preview
private fun Preview57(){
    HideTextOneByOne()
}

@Composable
fun HideTextOneByOne() {
    val text = "45.535.232 تومان"
    var isAnimating by remember { mutableStateOf(false) }
    val visibleCharacters = remember { Animatable(text.length.toFloat()) }
    val coroutineScope = rememberCoroutineScope() // To launch coroutines

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(50.dp)
                .background(Color.White)
        ) {
            // Display only the visible characters
            Text(
                text = text.take(visibleCharacters.value.toInt()),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (!isAnimating) {
                    isAnimating = true
                    coroutineScope.launch {
                        visibleCharacters.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(
                                durationMillis = text.length * 50, // Each character hides in 200ms
                                easing = LinearEasing
                            )
                        )
                        isAnimating = false
                    }
                }
            },
            enabled = !isAnimating
        ) {
            Text(text = "Hide Text")
        }
    }
}
