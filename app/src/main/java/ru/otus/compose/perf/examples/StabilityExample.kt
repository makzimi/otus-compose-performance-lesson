package ru.otus.compose.perf.examples

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text

data class Contact(
    var name: String,
)

@Composable
fun ContactDetails(contact: Contact) {
    Text(text = contact.name)
}
