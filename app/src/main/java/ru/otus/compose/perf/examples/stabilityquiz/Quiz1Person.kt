package ru.otus.compose.perf.examples.stabilityquiz

import androidx.compose.runtime.MutableState

enum class AccountType {
    Regular,
    Gold,
    VIP;
}

class Quiz1Person(
    val name: String,
    val age: Int,
    val accountType: AccountType,
    val lastStatus: MutableState<String>
)
