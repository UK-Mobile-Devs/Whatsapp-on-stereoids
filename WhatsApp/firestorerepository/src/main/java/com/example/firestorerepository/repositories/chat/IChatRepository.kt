package com.example.firestorerepository.repositories.chat

import com.example.firestorerepository.datatypes.Conversation
import io.reactivex.rxjava3.core.Observable

interface IChatRepository {
    fun getConversationList() : Observable<List<Conversation>>
}