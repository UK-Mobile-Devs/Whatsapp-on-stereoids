package com.example.whatsapp.helper

import android.app.Activity
import androidx.core.view.isVisible
import androidx.test.espresso.IdlingResource
import com.example.whatsapp.R
import com.example.whatsapp.ui.MainActivity
import com.google.android.material.appbar.AppBarLayout


/**
 * Idler that waits for UI to be displayed before continuing with the test.
 * Currently not used but I will leave the implementation here.
 * *.- Oskar Lasota
 */
class HomeFragmentIdlingResource(private val activity: Activity) : IdlingResource {

    private lateinit var resourceCallBack: IdlingResource.ResourceCallback

    override fun getName(): String = javaClass.simpleName

    override fun isIdleNow(): Boolean {
        if (activity !is MainActivity) {
            resourceCallBack.onTransitionToIdle()
            return true
        }
        val appBarLayout = activity.findViewById<AppBarLayout>(R.id.tabCalls)

        if (appBarLayout.isVisible) resourceCallBack.onTransitionToIdle()

        return appBarLayout.isVisible
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.resourceCallBack = callback
    }
}