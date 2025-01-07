package ru.otus.compose.perf.examples.stabilityquiz

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import java.time.LocalDate

class Quiz2Product(
    val title: String,
    val description: Int,
    val ingredients: ImmutableList<Ingredient>,
    val expireDate: ExpireDate,
)

@Immutable
class ExpireDate (
    val date: LocalDate
)

class Ingredient (
    val id: String,
    val name: String,
    val option: List<SizeOption>,
)

class SizeOption(
    val id: String,
    val name: String,
)
