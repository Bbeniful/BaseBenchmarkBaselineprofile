package com.bbeniful.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "com.bbeniful.banchmarkandbaselineprofile",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()

    }

    @Test
    fun scroll() = benchmarkRule.measureRepeated(
        packageName = "com.bbeniful.banchmarkandbaselineprofile",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
        scrollDown()
    }

    @Test
    fun scrollAndNavigate() = benchmarkRule.measureRepeated(
        packageName = "com.bbeniful.banchmarkandbaselineprofile",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
        scrollAndNavigateToOtherScreen()
    }

   /* @Test
    fun scrollAndNavigateAndBack() = benchmarkRule.measureRepeated(
        packageName = "com.bbeniful.banchmarkandbaselineprofile",
        metrics = listOf(FrameTimingMetric()),
        iterations = 3,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
        scrollAndNavigateToOtherScreenAndGoBack()
    }*/
}

fun MacrobenchmarkScope.scrollDown() {
    val list = device.findObject(By.res("item_list"))
    device.waitForIdle()
    list.setGestureMargin(device.displayWidth / 5)
    list.fling(Direction.DOWN)

    device.findObject(By.text("appletree 38"))

}

fun MacrobenchmarkScope.scrollAndNavigateToOtherScreen() {
    val list = device.findObject(By.res("item_list"))
    device.waitForIdle()
    list.setGestureMargin(device.displayWidth/5)
    list.fling(Direction.DOWN)
    device.findObject(By.res("text_box_50")).click()

    device.wait(Until.hasObject(By.text("appletree at 50")), 5000)
}

fun MacrobenchmarkScope.scrollAndNavigateToOtherScreenAndGoBack(){
    val list = device.findObject(By.res("item_list"))
    device.waitForIdle()
    list.setGestureMargin(device.displayWidth / 5)
    list.fling(Direction.DOWN)
    device.findObject(By.res("text_box_50")).click()
    device.wait(Until.hasObject(By.text("appletree at 50")), 5000)
    device.pressBack()
    device.waitForIdle()
    list.setGestureMargin(device.displayWidth / 5)
    list.fling(Direction.UP)
    device.findObject(By.res("text_box_5")).click()

    device.wait(Until.hasObject(By.text("appletree at 5")), 5000)
}