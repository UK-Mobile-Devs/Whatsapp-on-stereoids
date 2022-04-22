package com.example.whatsapp.ui.fragments.messenger.group

import android.os.Bundle
import com.example.whatsapp.ui.fragments.messenger.BaseMessengerFragment
import com.example.whatsapp.utils.Constants.GROUP_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupMessengerFragment : BaseMessengerFragment() {

    private lateinit var groupId: String

    override fun initArgs(arguments: Bundle) {
        groupId = arguments.getString(GROUP_ID) ?: throw IllegalArgumentException("Missing group chat id")
    }

    override fun observeViewModel() {
        viewModel
    }




}