package com.example.whatsapp.utils

import com.example.firestorerepository.datatypes.Conversation
import com.example.whatsapp.ui.fragments.home.chats.ConversationSelectionType

fun List<Long>.conversationSelectionType(conversations : List<Conversation>) : ConversationSelectionType {

    val selectedItems = this.map {
        conversations[it.toInt()]
    }

    val selectedGroupChat = selectedItems
        .filter { it.isGroup }

    val selectedDirectMessage = selectedItems
        .filter { !it.isGroup }

    return if(selectedGroupChat.size > 1) {
        ConversationSelectionType.MULTIPLE_GROUPS
    }
    else if (selectedDirectMessage.size > 1){
        ConversationSelectionType.MULTIPLE_CHATS
    }
    else if (selectedGroupChat.isNotEmpty() && selectedDirectMessage.isNotEmpty()) {
        ConversationSelectionType.MIXTURE
    } else if (selectedGroupChat.size == 1) {
        ConversationSelectionType.GROUP
    } else {
        ConversationSelectionType.DIRECT
    }
}

fun <T> List<T>.getSelectedItems(selectedIndexes : List<Long>) : List<T> {
    return selectedIndexes.map {
        this[it.toInt()]
    }
}