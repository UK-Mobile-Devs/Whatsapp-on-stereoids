package com.example.firestorerepository.repositories.messenger

import com.example.firestorerepository.datatypes.Conversation
import io.reactivex.rxjava3.core.Observable

interface MessengerRepository{
    fun getConversation(): Observable<Conversation>
}