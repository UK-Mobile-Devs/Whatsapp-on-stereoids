package com.example.whatsapp.ui.fragments.settings.account.privacy

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.whatsapp.R
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class privacyFragment : Fragment(R.layout.fragment_account_information) {
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }


}