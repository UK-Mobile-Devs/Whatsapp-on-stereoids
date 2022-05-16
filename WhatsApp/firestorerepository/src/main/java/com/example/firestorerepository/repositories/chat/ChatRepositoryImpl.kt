package com.example.firestorerepository.repositories.chat

import com.example.firestorerepository.datatypes.Conversation
import io.reactivex.rxjava3.core.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor() : ChatRepository {

    //region IChatRepository
    override fun getConversationList(): Observable<List<Conversation>> {
        return Observable.create{ emitter ->
            val data = listOf(Conversation(UUID.randomUUID().toString(), false), Conversation(UUID.randomUUID().toString(), true), Conversation(UUID.randomUUID().toString(), true), Conversation(UUID.randomUUID().toString(), false))
            emitter.onNext(data)
        }
    }
    //endregion

}