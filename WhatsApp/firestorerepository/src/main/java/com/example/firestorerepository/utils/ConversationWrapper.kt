package com.example.firestorerepository.utils

import com.example.firestorerepository.datatypes.Conversation
import com.example.firestorerepository.datatypes.Message
import com.example.firestorerepository.models.ConversationDTO
import com.example.firestorerepository.models.MessageDTO
import com.example.firestorerepository.models.MessageType

object ConversationWrapper {

    fun Conversation.wrapConversationIntoDTO(): ConversationDTO {
        return ConversationDTO(
            this.uid,
            this.messages.map { it.wrapMessageIntoDTO() },
            this.isGroup,
            this.timeReceived,
            this.timeRead
        )
    }

    private fun Message.wrapMessageIntoDTO(): MessageDTO {
        return MessageDTO(
            this.id,
            this.content,
            if ((0..2).random() == 0) MessageType.INCOMING else MessageType.PERSONAL
        )
    }

}