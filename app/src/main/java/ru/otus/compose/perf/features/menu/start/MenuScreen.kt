package ru.otus.compose.perf.features.menu.start

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tracing.trace
import coil.compose.AsyncImage
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import ru.otus.compose.perf.R
import ru.otus.compose.perf.features.CommonViewModel
import ru.otus.compose.perf.features.menu.MenuState
import ru.otus.compose.perf.features.menu.MenuState.Ingredient

@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    viewModel: CommonViewModel = hiltViewModel(),
) {
    val menuState by viewModel.menu.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        MenuScreenContent(items = menuState.items)

        if (menuState.items.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}


@Composable
fun MenuScreenContent(
    items: List<MenuState.MenuItem>,
    modifier: Modifier = Modifier,
) {
    val statusBarsInsets = WindowInsets.systemBars.asPaddingValues()

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .testTag("list_of_items"),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            top = statusBarsInsets.calculateTopPadding(),
            bottom = statusBarsInsets.calculateBottomPadding() + 60.dp,
            start = 20.dp,
            end = 20.dp
        ),
        columns = GridCells.Fixed(2)
    ) {
        items(items) { item -> MenuItem(item) }
    }
}

@Composable
fun MenuItem(
    item: MenuState.MenuItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Box {
            AsyncImage(
                model = item.image,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .shadow(8.dp, RoundedCornerShape(12.dp)),
                contentDescription = null,
                placeholder = imagePlaceholder(),
                contentScale = ContentScale.Crop
            )

            DateAdded(
                dateAdded = item.dateAdded,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )

            ItemIngredients(
                ingredients = item.ingredients,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun imagePlaceholder() = trace("ImagePlaceholder") {
    painterResource(R.drawable.placeholder)
}

@Composable
fun DateAdded(dateAdded: Instant, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var currentTimeZone: TimeZone by remember { mutableStateOf(TimeZone.currentSystemDefault()) }

    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                currentTimeZone = TimeZone.currentSystemDefault()
            }
        }

        context.registerReceiver(receiver, IntentFilter(Intent.ACTION_TIMEZONE_CHANGED))

        onDispose { context.unregisterReceiver(receiver) }
    }

    Text(
        text = dateAdded.format(currentTimeZone),
        style = MaterialTheme.typography.labelMedium,
        modifier = modifier
    )
}

@Composable
fun ItemIngredients(
    ingredients: List<Ingredient>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(ingredients) { ItemIngredient(it) }
    }
}

@Composable
fun ItemIngredient(ingredient: Ingredient) = trace("ItemIngredient") {
    AsyncImage(
        model = ingredient.image,
        modifier = Modifier
            .size(20.dp)
            .aspectRatio(1f)
            .border(2.dp, Color.White, RoundedCornerShape(12.dp))
            .shadow(2.dp, RoundedCornerShape(12.dp)),
        contentDescription = null,
        placeholder = imagePlaceholder(),
        contentScale = ContentScale.Crop
    )
}

fun Instant.format(timeZone: TimeZone): String = trace("Instant.format") {
    val dt = toLocalDateTime(timeZone)
    val day = dt.dayOfMonth.toString().padStart(2, '0')
    val month = dt.monthNumber.toString().padStart(2, '0')
    val year = dt.year.toString()

    "$day.$month.$year"
}
