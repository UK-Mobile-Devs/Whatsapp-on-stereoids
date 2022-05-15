package com.example.whatsapp.utils

import com.example.firestorerepository.datatypes.Conversation
import com.example.whatsapp.ui.fragments.home.chats.ConversationSelectionType

fun List<Conversation>.conversationSelectionType() : ConversationSelectionType {
    val group = this.firstOrNull { it.isGroup } != null
    val individual = this.firstOrNull { !it.isGroup } != null

    return if (group && individual) {
        ConversationSelectionType.MIXTURE
    } else if (group) {
        ConversationSelectionType.GROUP
    } else {
        ConversationSelectionType.INDIVIDUAL
    }
}