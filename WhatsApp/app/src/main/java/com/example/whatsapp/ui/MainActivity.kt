package com.example.whatsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.whatsapp.R
import com.example.whatsapp.ui.fragments.home.HomeFragmentVM

class MainActivity : AppCompatActivity() {

    val viewModel : HomeFragmentVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}