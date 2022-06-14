package com.example.whatsapp.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.whatsapp.BaseFragmentTest
import com.example.whatsapp.R
import com.example.whatsapp.helper.selectTabAtPosition
import com.example.whatsapp.helper.waitFor
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test


/**
 *  Home Fragment must be the first screen the user sees after registering/login
 *  Currently we are using a delay for all navigations to avoid flaky tests (need to find a better alternative)
 */
class HomeFragmentTest : BaseFragmentTest() {

    private val CAMERA_TAB_INDEX = 0
    private val HOME_TAB_INDEX = 1
    private val STATUS_TAB_INDEX = 2
    private val CALLS_TAB_INDEX = 3

    @Before
    override fun setup() {
        super.setup()
    }

    //this test can be replaced when we write tests that interact with each visible component
    @Test
    fun view_successfully_rendered() {
        onView(
            allOf(
                withId(R.drawable.ic_camera),
                isDescendantOfA(withId(R.id.tlNavigation))
            )
        ) //could not check if camera icon is visible
        onView(withText(R.string.chats)).check(matches(isDisplayed()))
        onView(withText(R.string.status)).check(matches(isDisplayed()))
        onView(withText(R.string.calls)).check(matches(isDisplayed()))
        onView(withId(R.id.vpHomeScreen)).check(matches(isDisplayed()))
    }

    @Test
    fun fab_click_navigates_to_contacts_selection_screen_successfully() {
        //when
        onView(withId(R.id.fabNewConversation)).perform(click())

        onView(isRoot()).perform(waitFor(100))

        //verify
        onView(withId(R.id.rvMessageContacts)).check(matches(isDisplayed()))
    }

    @Test
    fun home_tab_click_navigates_to_home_screen_successfully() {
        //when
        onView(withId(R.id.tlNavigation)).perform(selectTabAtPosition(HOME_TAB_INDEX))

        onView(isRoot()).perform(waitFor(100))

        //verify
        onView(withId(R.id.rvChats)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun status_tab_click_navigates_to_status_screen_successfully() {
        //when
        onView(withId(R.id.tlNavigation)).perform(selectTabAtPosition(STATUS_TAB_INDEX))

        onView(isRoot()).perform(waitFor(100))

        //verify
        onView(withId(R.id.rvStatus)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun camera_tab_click_navigates_to_camera_screen_successfully() {
        //when
        onView(withId(R.id.tlNavigation)).perform(selectTabAtPosition(CAMERA_TAB_INDEX))

        onView(isRoot()).perform(waitFor(100))

        //verify
        onView(withId(R.id.camera_capture_button)).check(matches(isDisplayed()))
    }

    @Test
    fun calls_tab_click_navigates_to_calls_screen_successfully() {
        //when
        onView(withId(R.id.tlNavigation)).perform(selectTabAtPosition(CALLS_TAB_INDEX))

        onView(isRoot()).perform(waitFor(100))

        //verify
        onView(withId(R.id.fragment_calls_layout)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

}