package ru.otus.compose.perf.features.menu

import kotlinx.collections.immutable.ImmutableList

data class MenuState(
    val items: ImmutableList<MenuItem>
) {
    data class MenuItem(
        val id: String,
        val name: String,
        val dateAdded: kotlinx.datetime.Instant,
        val image: String,
        val ingredients: ImmutableList<Ingredient>
    )

    data class Ingredient(
        val id: String,
        val name: String,
        val image: String,
    )
}
