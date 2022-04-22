package com.example.whatsapp.ui.fragments.messenger.single

import android.os.Bundle
import com.example.whatsapp.ui.fragments.messenger.BaseMessengerFragment


class SingleMessengerFragment : BaseMessengerFragment() {

    private lateinit var contactId: String

    override fun initArgs(arguments: Bundle) {
        contactId = arguments.getString("contactId") ?: throw IllegalArgumentException("Missing contact user id")
    }

    override fun observeViewModel() {
        viewModel
    }


}