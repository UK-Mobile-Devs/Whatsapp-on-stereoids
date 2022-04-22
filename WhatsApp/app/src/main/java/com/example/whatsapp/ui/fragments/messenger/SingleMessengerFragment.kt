package com.example.whatsapp.ui.fragments.messenger

import android.os.Bundle


class SingleMessengerFragment : BaseMessengerFragment() {

    private lateinit var userId: String

    override fun initArgs(arguments: Bundle) {
        userId = arguments.getString("userId") ?: throw IllegalArgumentException("Missing user id")
    }

    override fun observeViewModel() {
        viewModel
    }


}