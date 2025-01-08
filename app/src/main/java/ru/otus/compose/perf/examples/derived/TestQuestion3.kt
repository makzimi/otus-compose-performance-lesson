package ru.otus.compose.perf.examples.derived

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DerivedQ3(items: List<ColorItem>) {
    val listState = rememberLazyListState()

    val itemsCount = remember {
        derivedStateOf { items.size }
    }

    Column {
        Text(
            text = "Size: ${itemsCount.value}",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray)
                .padding(20.dp)
        )
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(items) { item ->
                ListItem(item = item)
            }
        }
    }
}

@Composable
fun ListItem(item: ColorItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = item.color,
                    shape = CircleShape
                )

        )
        Text(text = item.name, modifier = Modifier.padding(start = 16.dp))
    }
}

data class ColorItem(
    val color: Color,
    val name: String
)

fun generateColorList(): List<ColorItem> {
    return listOf(
        ColorItem(Color(0xFF5D3FD3), "Velvet Purple"), // Заменён Ebony Black
        ColorItem(Color(0xFFC3073F), "Jazzberry Jam"),
        ColorItem(Color(0xFF6F2232), "Dark Ruby"),
        ColorItem(Color(0xFF950740), "Raspberry Wine"),
        ColorItem(Color(0xFFFFD369), "Saffron Glow"),
        ColorItem(Color(0xFF4ECCA3), "Mint Leaf"),
        ColorItem(Color(0xFF232931), "Charcoal Gray"),
        ColorItem(Color(0xFFFF847C), "Coral Blush"),
        ColorItem(Color(0xFF40BFC1), "Tropical Blue"),
        ColorItem(Color(0xFFF67280), "Watermelon Pink"),
        ColorItem(Color(0xFF364F6B), "Steel Blue"),
        ColorItem(Color(0xFFF8B400), "Sunshine Yellow"),
        ColorItem(Color(0xFFD9CAB3), "Sand Beige"),
        ColorItem(Color(0xFFFF6F61), "Living Coral"),
        ColorItem(Color(0xFF8E44AD), "Amethyst"),
        ColorItem(Color(0xFF34495E), "Midnight Blue"),
        ColorItem(Color(0xFFE17055), "Persimmon"),
        ColorItem(Color(0xFF81ECEC), "Aqua Mist"),
        ColorItem(Color(0xFF00B894), "Emerald Green"),
        ColorItem(Color(0xFF2D3436), "Obsidian")
    )
}

@Preview(showBackground = true)
@Composable
fun DerivedQ3Preview() {
    Surface {
        DerivedQ3(generateColorList())
    }
}