package com.example.firestorerepository.repositories.chat

import com.example.firestorerepository.datatypes.Conversation
import com.example.firestorerepository.datatypes.Message
import io.reactivex.rxjava3.core.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor() : ChatRepository {

    //region IChatRepository
    override fun getConversationList(): Observable<List<Conversation>> {
        return Observable.create{ emitter ->
            Conversation(UUID.randomUUID().toString(), listOf(Message("", ""), false)),
            Conversation(UUID.randomUUID().toString(),listOf(Message("", ""), false)),
            Conversation(UUID.randomUUID().toString(), listOf(Message("", ""), true)),
            Conversation(UUID.randomUUID().toString(), listOf(Message("", ""), true))
            emitter.onNext(data)
        }
    }
    //endregion

}