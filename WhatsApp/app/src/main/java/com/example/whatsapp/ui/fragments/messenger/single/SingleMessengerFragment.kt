package com.example.whatsapp.ui.fragments.messenger.single

import android.os.Bundle
import com.example.whatsapp.ui.fragments.messenger.BaseMessengerFragment
import com.example.whatsapp.utils.Constants.CONTACT_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleMessengerFragment : BaseMessengerFragment() {

    private lateinit var contactId: String

    override fun initArgs(arguments: Bundle) {
        contactId = arguments.getString(CONTACT_ID) ?: throw IllegalArgumentException("Missing contact user id")
    }

    override fun observeViewModel() {
        viewModel
    }


}