package ru.otus.compose.perf.examples.derived

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DerivedQ1() {
    var firstName by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }

    val fullName = remember {
        derivedStateOf { firstName + secondName }
    }

    Column(modifier = Modifier.padding(20.dp)) {
        TextField(
            value = firstName,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = { firstName = it },
            label = {
                Text("First Name")
            }
        )
        TextField(
            value = secondName,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = { secondName = it },
            label = {
                Text("Second Name")
            }
        )
        Box(
            modifier = Modifier
                .padding(top = 20.dp)
                .height(50.dp)
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(start = 20.dp),
        ) {
            Text(
                text = fullName.value,
                modifier = Modifier.align(CenterStart)
            )
        }
    }
}

@Preview
@Composable
fun DerivedQ1Preview() {
    Surface { DerivedQ1() }
}