package com.example.whatsapp.utils

import com.example.firestorerepository.datatypes.Conversation
import com.example.whatsapp.ui.fragments.home.chats.ConversationSelectionType

fun List<Long>.conversationSelectionType(items : List<Conversation>) : ConversationSelectionType {

    val newList = this.map {
        items[it.toInt()]
    }

    val group = newList.firstOrNull { it.isGroup } != null
    val individual = newList.firstOrNull { !it.isGroup } != null

    return if (group && individual) {
        ConversationSelectionType.MIXTURE
    } else if (group) {
        ConversationSelectionType.GROUP
    } else {
        ConversationSelectionType.INDIVIDUAL
    }
}