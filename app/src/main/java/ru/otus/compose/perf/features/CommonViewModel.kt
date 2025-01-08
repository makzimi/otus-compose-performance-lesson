package ru.otus.compose.perf.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlin.random.Random
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import ru.otus.compose.perf.features.menu.MenuState
import ru.otus.compose.perf.features.menu.MenuState.Ingredient
import java.util.UUID

class CommonViewModel : ViewModel() {
    private var _menu = MutableStateFlow(MenuState(items = persistentListOf()))
    val menu = _menu.asStateFlow()

    init {
        viewModelScope.launch {
            delay(500)
            _menu.value = generateMenu()
        }
    }
}

private fun generateMenu(): MenuState {
    val items = List(50) { index ->
        MenuState.MenuItem(
            id = UUID.randomUUID().toString(),
            name = names[index % names.size],
            dateAdded = Clock.System.now() - stableRandom.nextInt(48 * 60).minutes,
            image = images[index % images.size],
            ingredients = generateIngredients().toPersistentList()
        )
    }.toPersistentList()

    return MenuState(
        items = items
    )
}

private fun generateIngredients(): List<Ingredient> {
    val size = stableRandom.nextInt(3, 10)
    return List(size) { index ->
        Ingredient(
            id = UUID.randomUUID().toString(),
            name = ingredientNames[index % ingredientNames.size],
            image = ingredientImages[index % ingredientImages.size],
        )
    }.toPersistentList()
}

private val names =
    listOf(
        "Chicken Classic",
        "Crispy Bacon Crunch",
        "Cheesy Deluxe",
        "Veggie Supreme",
        "Turkey & Pesto",
        "Pulled Pork & Slaw",
        "Spicy Tuna Melt",
        "Roast Beef & Horseradish",
        "Mediterranean Falafel Pita",
        "Eggplant Parm Sub",
        "Lavender Honey Latte",
        "Maple Cardamom Cold Brew",
        "Rosemary Lemon Iced Tea",
        "Turmeric Ginger Latte",
        "Charcoal Black Lemonade",
    )

private val images = listOf(
    "https://makzimi.github.io/img/perf/ic_sand_01.webp",
    "https://makzimi.github.io/img/perf/ic_sand_02.webp",
    "https://makzimi.github.io/img/perf/ic_sand_03.webp",
    "https://makzimi.github.io/img/perf/ic_sand_04.webp",
    "https://makzimi.github.io/img/perf/ic_sand_05.webp",
    "https://makzimi.github.io/img/perf/ic_sand_06.webp",
    "https://makzimi.github.io/img/perf/ic_sand_07.webp",
    "https://makzimi.github.io/img/perf/ic_sand_08.webp",
    "https://makzimi.github.io/img/perf/ic_sand_09.webp",
    "https://makzimi.github.io/img/perf/ic_sand_10.webp",
    "https://makzimi.github.io/img/perf/ic_sand_11.webp",
    "https://makzimi.github.io/img/perf/ic_sand_12.webp",
    "https://makzimi.github.io/img/perf/ic_sand_13.webp",
    "https://makzimi.github.io/img/perf/ic_sand_14.webp",
    "https://makzimi.github.io/img/perf/ic_sand_15.webp",
    "https://makzimi.github.io/img/perf/ic_sand_16.webp",
)

private val ingredientNames =
    listOf(
        "Avocado Slices",
        "Jalapeno Rings",
        "Caramelized Onions",
        "Crispy Bacon Strips",
        "Fresh Basil Leaves",
    )

private val ingredientImages = listOf(
    "https://makzimi.github.io/img/perf/ic_ingr_01.webp",
    "https://makzimi.github.io/img/perf/ic_ingr_02.webp",
    "https://makzimi.github.io/img/perf/ic_ingr_03.webp",
    "https://makzimi.github.io/img/perf/ic_ingr_04.webp",
    "https://makzimi.github.io/img/perf/ic_ingr_05.webp",
)

private val stableRandom = Random(0)
