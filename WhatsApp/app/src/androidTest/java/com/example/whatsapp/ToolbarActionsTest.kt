package com.example.whatsapp

import androidx.test.espresso.Espresso
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before

open class ToolbarActionsTest : BaseFragmentTest() {


    @Before
    override fun setup() {
        super.setup()
        //navigate to uat
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.openActionBarOverflowOrOptionsMenu(appContext)
    }

}