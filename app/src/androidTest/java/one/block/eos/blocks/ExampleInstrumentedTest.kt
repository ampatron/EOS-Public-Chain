package one.block.eos.blocks

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("one.block.eos.blocks", appContext.packageName)
    }

//    @get:Rule
//    val activityRule = ActivityTestRule(MainActivity::class.java)
//
//    @Test fun buttonOpenDetailsScreen() {
//        // make sure that button is available
//        onView(withId(R.id.btn_load_blocks)).perform(click()).check(intended())
//    }

}

