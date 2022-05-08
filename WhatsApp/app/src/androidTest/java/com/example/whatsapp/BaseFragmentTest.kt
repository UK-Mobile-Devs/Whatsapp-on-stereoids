package com.example.whatsapp

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.whatsapp.ui.MainActivity
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
open class BaseFragmentTest {

    @Before
    open fun setup(){
        //setup activity
        ActivityScenario.launch(MainActivity::class.java)
    }

}