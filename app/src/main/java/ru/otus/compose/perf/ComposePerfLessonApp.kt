package ru.otus.compose.perf

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.otus.compose.perf.features.animation.start.FlyingCatScreen
import ru.otus.compose.perf.features.menu.start.MenuScreen
import ru.otus.compose.perf.ui.HomeScreen
import ru.otus.compose.perf.ui.theme.AppTheme
import ru.otus.compose.perf.ui.theme.ComposePerfLessonTheme

@Composable
fun ComposePerfLessonApp(
) {
    ComposePerfLessonTheme {
        Surface(color = AppTheme.colors.background) {
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
            }
        }
    }
}
