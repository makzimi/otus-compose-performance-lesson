package ru.otus.compose.perf.measure

import android.graphics.Point
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.ExperimentalMetricApi
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.Metric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.TraceSectionMetric
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalMetricApi::class)
class MenuScreenBenchmark : AbstractBenchmark(StartupMode.COLD) {

    @Test
    fun accelerateHeavyScreenCompilationFull() = benchmark(CompilationMode.Full())

    override val metrics: List<Metric> =
        listOf(
            FrameTimingMetric(),
            TraceSectionMetric("ImagePlaceholder", TraceSectionMetric.Mode.Sum),
            TraceSectionMetric("RegisterReceiver", TraceSectionMetric.Mode.Sum),
            TraceSectionMetric("ItemIngredient", TraceSectionMetric.Mode.Sum)
        )

    override fun MacrobenchmarkScope.measureBlock() {
        pressHome()

        startActivityAndWait()
        device.findObject(By.text("Menu example")).clickAndWait(Until.newWindow(), 1_000)

        device.wait(Until.hasObject(By.res("list_of_items")), 5_000)
        val feed = device.findObject(By.res("list_of_items"))
        feed.setGestureMargin(device.displayWidth / 5)

        repeat(2) {
            feed.drag(Point(feed.visibleCenter.x, feed.visibleBounds.top))
            Thread.sleep(500)
        }
    }
}
