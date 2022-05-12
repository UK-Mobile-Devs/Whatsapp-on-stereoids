package com.example.whatsapp.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.whatsapp.BaseFragmentTest
import com.example.whatsapp.R
import org.junit.Test

/**
 *  Home Fragment must be the first screen the user sees after registering/login
 */
class HomeFragmentTest : BaseFragmentTest() {


    //this test can be replaced when we write tests that interact with each visible component
    @Test
    fun view_successfully_rendered() {
        //onView(withId(R.id.tabCamera)).check(matches(isDisplayed()))
        onView(withText(R.string.chats)).check(matches(isDisplayed()))
        onView(withText(R.string.status)).check(matches(isDisplayed()))
        onView(withText(R.string.calls)).check(matches(isDisplayed()))
        onView(withId(R.id.fabNewConversation)).check(matches(isDisplayed()))
        onView(withId(R.id.vpHomeScreen)).check(matches(isDisplayed()))
    }


}