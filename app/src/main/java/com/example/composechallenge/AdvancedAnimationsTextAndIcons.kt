
// Advanced Jetpack Compose Animations for Text and Icons

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RotatingIconExample() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    Icon(
        imageVector = Icons.Default.Refresh,
        contentDescription = null,
        modifier = Modifier
            .size(50.dp)
            .graphicsLayer(rotationZ = rotation),
        tint = Color.Blue
    )
}

@Composable
fun TextFadeInOutExample() {
    var visible by remember { mutableStateOf(true) }
    val alpha by animateFloatAsState(targetValue = if (visible) 1f else 0f)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { visible = !visible }) {
            Text("Toggle Fade")
        }
        Text(
            "Hello, Compose!",
            Modifier.alpha(alpha),
            fontSize = 24.sp,
            color = Color.Red
        )
    }
}

@Composable
fun TextSizeAnimationExample() {
    var expanded by remember { mutableStateOf(false) }
    val fontSize by animateFloatAsState(targetValue = if (expanded) 30f else 16f)

    Text(
        text = "Animated Text Size",
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    )
}

@Composable
fun IconBounceExample() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -20f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Icon(
        imageVector = Icons.Default.KeyboardArrowDown,
        contentDescription = null,
        modifier = Modifier
            .size(50.dp)
            .offset(y = offsetY.dp),
        tint = Color.Green
    )
}

@Composable
fun TextColorChangeExample() {
    var toggled by remember { mutableStateOf(false) }
    val color by animateColorAsState(targetValue = if (toggled) Color.Magenta else Color.Gray)

    Text(
        text = "Tap to Change Color",
        color = color,
        fontSize = 20.sp,
        modifier = Modifier
            .clickable { toggled = !toggled }
            .padding(8.dp)
    )
}

@Composable
fun TextSlideExample() {
    var toggled by remember { mutableStateOf(false) }
    val offsetX by animateDpAsState(targetValue = if (toggled) 100.dp else 0.dp)

    Text(
        text = "Slide Me!",
        fontSize = 18.sp,
        modifier = Modifier
            .offset(x = offsetX)
            .clickable { toggled = !toggled }
    )
}

@Composable
fun IconPulseExample() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = null,
        modifier = Modifier
            .size(50.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale),
        tint = Color.Red
    )
}
@Preview
@Composable
private fun Preview5(){
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        RotatingIconExample()
        TextFadeInOutExample()
        TextColorChangeExample()
        TextSizeAnimationExample()
        IconBounceExample()
        TextSlideExample()
        IconPulseExample()

    }
}
