package ru.otus.compose.perf.features.bouncing.finish

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.trace

private val smallSize = 64.dp
private val bigSize = 200.dp

@Composable
fun BouncingCircleScreen() = trace("BouncingCircleScreen") {
    var targetSize by remember { mutableStateOf(smallSize) }
    val size by animateDpAsState(
        targetValue = targetSize,
        label = "size",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessVeryLow
        )
    )

    Box(
        Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
    ) {
        Circle(
            size = size,
            modifier = Modifier.align(Alignment.Center)
        )
        Button(
            onClick = {
                targetSize = if (targetSize == smallSize) {
                    bigSize
                } else {
                    smallSize
                }
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text("Change Size")
        }
    }
}

@Composable
fun Circle(
    size: Dp,
    modifier: Modifier = Modifier,
) = trace("Circle") {
    Box(
        modifier = modifier
            .background(color = Color.Magenta, shape = CircleShape)
            .size(size)
    )
}
