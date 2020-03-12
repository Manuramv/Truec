package com.lemurians.truecaller

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule



@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    @Rule @JvmField var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp(){

    }

    @Test
    fun ischeckAllFieldsArePresentInTheView(){
       // val activityScenario: ActivityScenario<MainActivity> = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.txtEveryTenth))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.txtTenthchar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.txtWordCounter))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun clickAPICallButton(){
        Espresso.onView(ViewMatchers.withId(R.id.btnCallApi)).perform(ViewActions.click())
    }
}