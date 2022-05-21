package com.example.firestorerepository.repositories.chat

import com.example.firestorerepository.datatypes.CallHistory
import com.example.firestorerepository.datatypes.Conversation
import io.reactivex.rxjava3.core.Observable

interface ChatRepository {
    fun getConversationList() : Observable<List<Conversation>>
    fun getChatHistory() : Observable<List<CallHistory>>
}