package com.example.whatsapp.settings

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.whatsapp.R
import com.example.whatsapp.ToolbarActionsTest
import org.junit.Before
import org.junit.Test


class SettingsFragmentTest : ToolbarActionsTest() {

    @Before
    override fun setup() {
        super.setup()

        //navigate to uat from action bar
        onView(withText(R.string.settings)).perform(click())
    }

    @Test
    fun settings_page_successfully_loaded() {
        //verify
        onView(withId(R.id.layoutProfileRow)).check(matches(isDisplayed()))
        onView(withId(R.id.layoutAccountRow)).check(matches(isDisplayed()))
        onView(withId(R.id.viewSpacing)).check(matches(isDisplayed()))
        onView(withId(R.id.layoutChatsRow)).check(matches(isDisplayed()))
        onView(withId(R.id.layoutHelpRow)).check(matches(isDisplayed()))
        onView(withId(R.id.layoutNotificationsRow)).check(matches(isDisplayed()))
        onView(withId(R.id.layoutReferralRow)).check(matches(isDisplayed()))
        onView(withId(R.id.layoutStorageRow)).check(matches(isDisplayed()))
    }


}