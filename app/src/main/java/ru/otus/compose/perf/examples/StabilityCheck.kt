package ru.otus.compose.perf.examples

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import java.time.LocalDate

class StableClass(
    val counter: Pair<String, String>
)

class UnstableClass(
    val counter: Pair<LocalDate, LocalDate>
)

class UnstableClass2(
    val counter: Pair<*, *>
)

class StableClass2(
    val immutableValue: Int
)

class StableClass3(
    val mutableState: MutableState<String>
)

class UnstableClass3(
    var immutableValue: String
)

@Immutable
class StableClass4(
    val unstable: UnstableClass3
)
