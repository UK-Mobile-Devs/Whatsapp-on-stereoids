package com.example.whatsapp.ui.fragments.messenger.single

import android.os.Bundle
import com.example.whatsapp.ui.fragments.messenger.BaseMessengerFragment
import com.example.whatsapp.ui.fragments.messenger.MessengerAdapter
import com.example.whatsapp.utils.Constants.CONTACT_ID
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class SingleMessengerFragment : BaseMessengerFragment() {

    private lateinit var contactId: String

    private val messageAdapter = MessengerAdapter()

    override fun initArgs(arguments: Bundle) {
        contactId = arguments.getString(CONTACT_ID)
            ?: throw IllegalArgumentException("Missing contact user id")
    }

    override fun observeViewModel() {
        viewModel.getConversation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                messageAdapter.submitList(it.messages)
            }
    }


}