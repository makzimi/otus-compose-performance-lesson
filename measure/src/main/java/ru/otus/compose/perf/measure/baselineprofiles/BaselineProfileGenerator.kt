package ru.otus.compose.perf.measure.baselineprofiles

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RequiresApi(Build.VERSION_CODES.P)
@RunWith(AndroidJUnit4::class)
class BaselineProfileGenerator {
    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generateBaselineProfiles() = rule.collect(
        packageName = "ru.otus.compose.perf",
        includeInStartupProfile = true,
        maxIterations = 3,
    ) {
        startActivityAndWait()

        pressAndGoBack("Menu example")
        pressAndGoBack("Flying cat example")
        pressAndGoBack("Bouncing example")
    }

    private fun MacrobenchmarkScope.pressAndGoBack(buttonText: String) {
        device.wait(Until.hasObject(By.text(buttonText)), 3_000)
        device.findObject(By.text(buttonText)).clickAndWait(Until.newWindow(), 3_000)
        device.pressBack()
    }
}
