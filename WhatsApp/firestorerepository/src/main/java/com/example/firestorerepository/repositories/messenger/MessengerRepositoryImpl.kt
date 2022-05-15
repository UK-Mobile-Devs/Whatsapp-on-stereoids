package com.example.firestorerepository.repositories.messenger

import com.example.firestorerepository.datatypes.Conversation
import io.reactivex.rxjava3.core.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessengerRepositoryImpl @Inject constructor(): MessengerRepository {

    override fun getConversation(): Observable<Conversation> {
        return Observable.create{ emitter ->
            val data = Conversation(UUID.randomUUID().toString(), "")

            emitter.onNext(data)
        }
    }

}