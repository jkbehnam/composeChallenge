package com.example.composechallenge

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.io.path.Path
import kotlin.math.roundToInt


@Composable
@Preview
private fun Preview52(){
    DrawCubic()
}
@Composable
fun DrawCubic() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        val density = LocalDensity.current.density

        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp

        val screenWidthInPx = screenWidth.value * density

        // (x0, y0) is initial coordinate where path is moved with path.moveTo(x0,y0)
        var x0 by remember { mutableStateOf(0f) }
        var y0 by remember { mutableStateOf(0f) }

        /*
        Adds a cubic bezier segment that curves from the current point(x0,y0) to the
        given point (x3, y3), using the control points (x1, y1) and (x2, y2). thi
     */
        var x1 by remember { mutableStateOf(0f) }
        var y1 by remember { mutableStateOf(screenWidthInPx) }
        var x2 by remember { mutableStateOf(screenWidthInPx/2) }
        var y2 by remember { mutableStateOf(0f) }

        var x3 by remember { mutableStateOf(screenWidthInPx) }
        var y3 by remember { mutableStateOf(screenWidthInPx/2) }

        val path = remember { androidx.compose.ui.graphics.Path() }
        Canvas(
            modifier = Modifier
                .padding(8.dp)
                .shadow(1.dp)
                .background(Color.White)
                .size(screenWidth, screenWidth/2)
        ) {
            path.reset()
            path.moveTo(x0, y0)
            path.cubicTo(x1 = x1, y1 = y1, x2 = x2, y2 = y2, x3 = x3, y3 = y3)


            drawPath(
                color = Color.Green,
                path = path,
                style = Stroke(
                    width = 3.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                )
            )

            // Draw Control Points on screen
            drawPoints(
                listOf(Offset(x1, y1), Offset(x2, y2)),
                color = Color.Green,
                pointMode = PointMode.Points,
                cap = StrokeCap.Round,
                strokeWidth = 40f
            )
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {

            Text(text = "X0: ${x0.roundToInt()}")
            Slider(
                value = x0,
                onValueChange = { x0 = it },
                valueRange = 0f..screenWidthInPx,
            )

            Text(text = "Y0: ${y0.roundToInt()}")
            Slider(
                value = y0,
                onValueChange = { y0 = it },
                valueRange = 0f..screenWidthInPx,
            )

            Text(text = "X1: ${x1.roundToInt()}")
            Slider(
                value = x1,
                onValueChange = { x1 = it },
                valueRange = 0f..screenWidthInPx,
            )

            Text(text = "Y1: ${y1.roundToInt()}")
            Slider(
                value = y1,
                onValueChange = { y1 = it },
                valueRange = 0f..screenWidthInPx,
            )

            Text(text = "X2: ${x2.roundToInt()}")
            Slider(
                value = x2,
                onValueChange = { x2 = it },
                valueRange = 0f..screenWidthInPx,
            )

            Text(text = "Y2: ${y2.roundToInt()}")
            Slider(
                value = y2,
                onValueChange = { y2 = it },
                valueRange = 0f..screenWidthInPx,
            )

            Text(text = "X3: ${x3.roundToInt()}")
            Slider(
                value = x3,
                onValueChange = { x3 = it },
                valueRange = 0f..screenWidthInPx,
            )

            Text(text = "Y3: ${y3.roundToInt()}")
            Slider(
                value = y3,
                onValueChange = { y3 = it },
                valueRange = 0f..screenWidthInPx,
            )
        }
    }
}