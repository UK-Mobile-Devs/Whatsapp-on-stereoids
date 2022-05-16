package com.example.whatsapp.ui.fragments.home.chats

import com.example.firestorerepository.datatypes.Conversation
import com.example.firestorerepository.repositories.chat.ChatRepository
import com.example.firestorerepository.repositories.chat.ChatRepositoryImpl
import com.example.whatsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@HiltViewModel
class ChatsFragmentVM @Inject constructor(private val chatsRepo : ChatRepository) : BaseViewModel() {

    init {

    }

    fun getChats() : Observable<List<Conversation>> {
        return chatsRepo.getConversationList()
    }

}