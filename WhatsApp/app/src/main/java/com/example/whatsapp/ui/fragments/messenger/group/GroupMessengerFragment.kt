package com.example.whatsapp.ui.fragments.messenger.group

import android.os.Bundle
import com.example.whatsapp.ui.fragments.messenger.BaseMessengerFragment

class GroupMessengerFragment : BaseMessengerFragment() {

    private lateinit var groupId: String

    override fun initArgs(arguments: Bundle) {
        groupId = arguments.getString("groupId") ?: throw IllegalArgumentException("Missing group chat id")
    }

    override fun observeViewModel() {
        viewModel
    }


}