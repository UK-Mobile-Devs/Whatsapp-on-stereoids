package com.example.whatsapp.ui.fragments.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.whatsapp.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }


}