package ru.otus.compose.perf

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.otus.compose.perf.features.bouncing.start.BouncingCircleScreen
import ru.otus.compose.perf.features.flyingcat.start.FlyingCatScreen
import ru.otus.compose.perf.features.menu.start.MenuScreen
import ru.otus.compose.perf.ui.HomeScreen
import ru.otus.compose.perf.ui.theme.AppTheme
import ru.otus.compose.perf.ui.theme.ComposePerfLessonTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ComposePerfLessonApp() {
    ComposePerfLessonTheme {
        Surface(
            color = AppTheme.colors.background,
            modifier = Modifier
                .fillMaxSize()
                .semantics { testTagsAsResourceId = true },
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Home,
            ) {
                composable<Home> {
                    HomeScreen(navHostController = navController)
                }

                composable<MenuExample> {
                    MenuScreen()
                }

                composable<FlyingCatExample> {
                    FlyingCatScreen()
                }

                composable<BouncingCircleExample> {
                    BouncingCircleScreen()
                }
            }
        }
    }
}
