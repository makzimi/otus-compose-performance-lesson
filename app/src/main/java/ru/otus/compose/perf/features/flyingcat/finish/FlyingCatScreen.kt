package ru.otus.compose.perf.features.flyingcat.finish

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.tracing.trace
import ru.otus.compose.perf.R

@Composable
fun FlyingCatScreen() = trace("FlyingCatScreen") {
    val logo = painterResource(id = R.drawable.cat)
    var size by remember { mutableStateOf(IntSize.Zero) }
    val imagePosition by imagePosition(size = size, logoSize = logo.intrinsicSize)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onPlaced {
                size = it.size
            }
    ) {
        with(LocalDensity.current) {
            Image(
                painter = logo,
                contentDescription = null,
                modifier = Modifier.offset { imagePosition }
            )
        }
    }
}

@Composable
fun imagePosition(size: IntSize, logoSize: Size): State<IntOffset> =
    produceState(initialValue = IntOffset.Zero, size, logoSize) {
        if (size == IntSize.Zero) {
            this.value = IntOffset.Zero
            return@produceState
        }

        var xDirection = 1
        var yDirection = 1

        while (true) {
            withFrameMillis {
                value += IntOffset(x = MOVE_SPEED * xDirection, y = MOVE_SPEED * yDirection)

                if (value.x <= 0 || value.x >= size.width - logoSize.width) {
                    xDirection *= -1
                }

                if (value.y <= 0 || value.y >= size.height - logoSize.height) {
                    yDirection *= -1
                }
            }
        }
    }

internal const val MOVE_SPEED = 10
