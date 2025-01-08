package ru.otus.compose.perf.examples.derived

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DerivedQ2() {
    var username by remember { mutableStateOf("") }
    val buttonEnabled by remember {
        derivedStateOf {
            isUserNameValid(username)
        }
    }

    Column(modifier = Modifier.padding(20.dp)) {
        TextField(
            value = username,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = { username = it },
            label = {
                Text("User name")
            }
        )
        Button(
            onClick = { },
            modifier = Modifier
                .padding(top = 20.dp)
                .height(50.dp)
                .fillMaxWidth(),
            enabled = buttonEnabled
        ) {
            Text(text = "Submit")
        }
    }
}

fun isUserNameValid(username: String): Boolean {
    return username.isNotBlank() && onlyLetters(username)
}

fun onlyLetters(s: String): Boolean =
    (s.firstOrNull { !it.isLetter() } == null)

@Preview
@Composable
fun DerivedQ2Preview() {
    Surface { DerivedQ2() }
}