package com.qdev.myarchive

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.qdev.myarchive.helper.ViewsWithLifecycleDispatcher

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ViewsWithLifecycleDispatcherTest {

    interface TestViewEvents {
        fun test1()
        fun test2()
    }

    class TestCheck {

        private var testPoints = ""
        fun testPoint(pointName: String) {
            if (testPoints.isNotEmpty()) {
                testPoints +=" "
            }
            testPoints += pointName
        }

        fun differenceColumn(string1: String, string2: String){
            //TODO
        }

        fun controlSequence(stringCheck: String) {
            val columnWithDifference = testPoints.compareTo(stringCheck)
            assertTrue("ViewsWithLifecycleDispatcher: \ntestPointSequence: $testPoints"
                    + "\ncontrolSequence  : $stringCheck," +
                    " \ncolumnWithDifference: $columnWithDifference",
                    testPoints == stringCheck)
        }

    }

    private val testCheck = TestCheck()

    private var testIsOkString = "dispatch addView1 sendEvent1 point1.1 sendEvent2 point2.1 " +
            "dispatch sendEvent1 point1.1 sendEvent2 point2.1 " +
            "removeView1 addView1 sendEvent1 point1.1 addView2 sendEvent1 point1.1 " +
            "sendEvent2 point2.1 sendEvent1 point1.2 sendEvent2 point2.2"

    private val viewsWithLifecycleDispatcher = ViewsWithLifecycleDispatcher<TestViewEvents>()

    private val testViewEvents1 = TestViewEventImplement(1, testCheck)
    private val testViewEvents2 = TestViewEventImplement(2, testCheck)

    class TestViewEventImplement(val idNum: Int, val testCheck: TestCheck) : TestViewEvents {

        override fun test1() {
            testCheck.testPoint("point1.$idNum")
        }

        override fun test2() {
            testCheck.testPoint("point2.$idNum")
        }

    }

    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.qdev.myarchive", appContext.packageName)
    }

    @Test
    fun testFunctions() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        //TODO Need test without addView
        testCheck.testPoint("dispatch")
        viewsWithLifecycleDispatcher.dispatch {
            testCheck.testPoint("sendEvent1")
            it.test1()
            testCheck.testPoint("sendEvent2")
            it.test2()
        }
        testCheck.testPoint("addView1")
        viewsWithLifecycleDispatcher.addView(testViewEvents1)
        testCheck.testPoint("dispatch")
        viewsWithLifecycleDispatcher.dispatch {
            testCheck.testPoint("sendEvent1")
            it.test1()
            testCheck.testPoint("sendEvent2")
            it.test2()
        }
        testCheck.testPoint("removeView1")
        viewsWithLifecycleDispatcher.removeView(testViewEvents1)
        viewsWithLifecycleDispatcher.dispatch {
            testCheck.testPoint("sendEvent1")
            it.test1()
        }
        testCheck.testPoint("addView1")
        viewsWithLifecycleDispatcher.addView(testViewEvents1)
        testCheck.testPoint("addView2")
        viewsWithLifecycleDispatcher.addView(testViewEvents2)
        viewsWithLifecycleDispatcher.dispatch {
            testCheck.testPoint("sendEvent1")
            it.test1()
            testCheck.testPoint("sendEvent2")
            it.test2()
        }
        testCheck.controlSequence(testIsOkString)
    }
}