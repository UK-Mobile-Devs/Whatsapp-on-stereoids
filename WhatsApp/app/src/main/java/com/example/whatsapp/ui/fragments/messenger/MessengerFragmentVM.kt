package com.example.whatsapp.ui.fragments.messenger

import com.example.firestorerepository.models.ConversationDTO
import com.example.firestorerepository.repositories.messenger.MessengerRepository
import com.example.whatsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


@HiltViewModel
class MessengerFragmentVM @Inject constructor(
    private val messengerRepo: MessengerRepository
) : BaseViewModel() {


    fun getConversation(): Observable<ConversationDTO> {
        return messengerRepo.getConversation()
    }

}