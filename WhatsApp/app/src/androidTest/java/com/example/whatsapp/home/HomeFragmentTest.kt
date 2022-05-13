package com.example.whatsapp.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.whatsapp.BaseFragmentTest
import com.example.whatsapp.R
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test


/**
 *  Home Fragment must be the first screen the user sees after registering/login
 */
class HomeFragmentTest : BaseFragmentTest() {

    @Before
    override fun setup() {
        super.setup()
    }

    //this test can be replaced when we write tests that interact with each visible component
    @Test
    fun view_successfully_rendered() {
        onView(allOf(withId(R.drawable.ic_camera), isDescendantOfA(withId(R.id.tlNavigation)))) //could not check if camera icon is visible
        onView(withText(R.string.chats)).check(matches(isDisplayed()))
        onView(withText(R.string.status)).check(matches(isDisplayed()))
        onView(withText(R.string.calls)).check(matches(isDisplayed()))
        onView(withId(R.id.vpHomeScreen)).check(matches(isDisplayed()))
    }

    @Test
    fun fab_click_navigates_successfully(){
        //when
        onView(withId(R.id.fabNewConversation)).perform(click())

        //verify
        //todo check if contacts fragment is opened
    }

    @Test
    fun chat_click_navigates_to_messenger_screen(){
        //todo
    }
}