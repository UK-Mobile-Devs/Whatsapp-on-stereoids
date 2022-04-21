package com.example.whatsapp.ui.fragments.messenger

import com.example.firestorerepository.repositories.messenger.MessengerRepository
import com.example.whatsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MessengerFragmentVM @Inject constructor(
    private val messengerRepo : MessengerRepository
) : BaseViewModel() {
}