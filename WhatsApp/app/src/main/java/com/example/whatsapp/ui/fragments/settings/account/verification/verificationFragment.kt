package com.example.whatsapp.ui.fragments.settings.account.verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.whatsapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class verificationFragment : Fragment(R.layout.fragment_verification) {
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }


}