package com.github.fajaragungpramana.igit.module.main

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.github.fajaragungpramana.igit.constant.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.github.fajaragungpramana.igit.R
import com.github.fajaragungpramana.igit.module.adapter.UserAdapter

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class MainActivityTest {

    private lateinit var uiDevice: UiDevice

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun testUserSearch() {
        Espresso.onView(ViewMatchers.withId(R.id.tie_search_username))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.typeText("fajaragungpramana"))

        uiDevice.setOrientationLandscape()

        Thread.sleep(3000)

        uiDevice.setOrientationPortrait()

        Espresso.onView(ViewMatchers.withId(R.id.rv_user))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UserAdapter.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )

        Espresso.onView(ViewMatchers.withId(R.id.mtv_user_full_name))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        uiDevice.setOrientationLandscape()

        Thread.sleep(3000)

        uiDevice.setOrientationPortrait()
    }

}