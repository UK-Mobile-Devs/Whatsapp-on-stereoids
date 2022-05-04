package com.example.whatsapp.ui.fragments.settings.account.security

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.whatsapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class securityFragment : Fragment(R.layout.fragment_security) {
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }


}