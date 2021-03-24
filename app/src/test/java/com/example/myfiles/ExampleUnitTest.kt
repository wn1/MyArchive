package com.example.myfiles

import android.util.Log
import com.example.myfiles.helper.ViewsWithLifecycleDispatcher
import org.junit.Test

import org.junit.Assert.*

/**
 * Unit test implements of class ViewsWithLifecycleDispatcher
 */

class ViewsWithLifecycleDispatcherTest {

    interface TestViewEvents {
        fun test1()
        fun test2()
    }

    class TestCheck {

        private var testStringControl = ""
        fun control(stringControl: String) {
            testStringControl += " $stringControl"
        }

        fun check(stringCheck: String): Boolean {
            Log.d("ViewsWithLifecycleDispatcherTest", "testControl: $testStringControl")
            Log.d("ViewsWithLifecycleDispatcherTest", "checkControl: $stringCheck")
            if (testStringControl != stringCheck) {
                Log.e("ViewsWithLifecycleDispatcherTest", "Test no pass")
                return false
            } else {
                Log.d("ViewsWithLifecycleDispatcherTest", "Test pass!")
                return true
            }
        }

    }

    private val testCheck = TestCheck()

    private var testIsOkString = "addView1 dispatch sendEvent1 event1.1 sendEvent2 event2.1 " +
            "removeView1 sendEvent1 addView1 event1.1 addView2 sendEvent1 event1.1 " +
            "event 1.2 sendEvent2 event2.1 event 2.2"

    private val viewsWithLifecycleDispatcher = ViewsWithLifecycleDispatcher<TestViewEvents>()

    private val testViewEvents1 = TestViewEventImplement(1, testCheck)
    private val testViewEvents2 = TestViewEventImplement(2, testCheck)

    class TestViewEventImplement(val idNum: Int, val testCheck: TestCheck) : TestViewEvents {

        override fun test1() {
            testCheck.control("test1.$idNum")
        }

        override fun test2() {
            testCheck.control("test2.$idNum")
        }

    }

    @Test
    fun addition_isCorrect() {
        testCheck.check("addView1")
        viewsWithLifecycleDispatcher.addView(testViewEvents1)
        testCheck.check("dispatch")
        viewsWithLifecycleDispatcher.dispatch {
            testCheck.check("sendEvent1")
            it.test1()
            testCheck.check("sendEvent2")
            it.test2()
        }
        testCheck.check("removeView1")
        viewsWithLifecycleDispatcher.removeView(testViewEvents1)
        viewsWithLifecycleDispatcher.dispatch {
            testCheck.check("sendEvent1")
            it.test1()
        }
        testCheck.check("addView1")
        viewsWithLifecycleDispatcher.addView(testViewEvents1)
        testCheck.check("addView2")
        viewsWithLifecycleDispatcher.addView(testViewEvents2)
        viewsWithLifecycleDispatcher.dispatch {
            testCheck.check("sendEvent1")
            it.test1()
            testCheck.check("sendEvent2")
            it.test2()
        }
        assertFalse(testCheck.check(testIsOkString))
    }
}