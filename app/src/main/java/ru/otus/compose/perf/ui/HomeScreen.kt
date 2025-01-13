package ru.otus.compose.perf.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.otus.compose.perf.BouncingCircleExample
import ru.otus.compose.perf.FlyingCatExample
import ru.otus.compose.perf.MenuExample

@Composable
fun HomeScreen(
    navHostController: NavHostController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                navHostController.navigate(MenuExample)
            }
        ) {
            Text(text = "Menu example")
        }

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                navHostController.navigate(FlyingCatExample)
            }
        ) {
            Text(text = "Flying cat example")
        }

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                navHostController.navigate(BouncingCircleExample)
            }
        ) {
            Text(text = "Bouncing example")
        }
    }
}
