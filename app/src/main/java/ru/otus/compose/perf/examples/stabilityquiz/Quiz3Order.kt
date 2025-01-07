package ru.otus.compose.perf.examples.stabilityquiz

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import java.math.BigDecimal
import java.time.LocalDateTime

class Quiz3Order(
    val id: String,
    val items: ImmutableList<Item>,
    val totalCost: Double,
    val discount: Discount,
    val userData: Pair<String, String>,
    val expireDate: LocalDate,
)

class Item (
    val id: String,
    val title: String,
    val image: Image,
)

@Immutable
class LocalDate(
    val dateTime: LocalDateTime,
)

@Immutable
class Discount (
    val bonusName: String,
    val discountCost: BigDecimal,
)

sealed class Image {
    class ResImage(val res: Int): Image()
    class UrlImage(val url: String): Image()
}
