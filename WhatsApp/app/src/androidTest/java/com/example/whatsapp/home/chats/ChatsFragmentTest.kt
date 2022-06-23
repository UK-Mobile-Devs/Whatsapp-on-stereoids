package com.example.whatsapp.home.chats

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.whatsapp.BaseFragmentTest
import com.example.whatsapp.R
import com.example.whatsapp.helper.waitFor
import com.example.whatsapp.ui.fragments.home.chats.ChatsAdapter
import org.junit.Test


class ChatsFragmentTest : BaseFragmentTest() {

    val sut_index = 0

    @Test
    fun navigate_to_messenger_screen_success() {
        onView(isRoot()).perform(waitFor(100)) //todo replace delay with IdlingResource

        onView(withId(R.id.rvChats))
            .perform(actionOnItemAtPosition<ChatsAdapter.ChatsViewHolder>(sut_index, click()))

        onView(withId(R.id.layoutInteractor)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun chat_item_displays_all_fields() {
        onView(isRoot()).perform(waitFor(100)) //todo replace delay with IdlingResource

        //check views here
    }

}