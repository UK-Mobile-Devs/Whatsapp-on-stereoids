package com.example.firestorerepository.repositories.messenger

import com.example.firestorerepository.datatypes.Conversation
import com.example.firestorerepository.datatypes.Message
import com.example.firestorerepository.models.ConversationDTO
import com.example.firestorerepository.utils.ConversationWrapper.wrapConversationIntoDTO
import io.reactivex.rxjava3.core.Observable
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessengerRepositoryImpl @Inject constructor() : MessengerRepository {

    override fun getConversation(): Observable<ConversationDTO> {
        return Observable.create { emitter ->
            val data = Conversation(
                UUID.randomUUID().toString(),
                listOf(Message("1", "example message"), Message("2", "example message"))
            )

            emitter.onNext(data.wrapConversationIntoDTO())
        }
    }

}
