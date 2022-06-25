package com.example.firestorerepository.models


import java.util.*

data class ConversationDTO(
    val uid: String,
    val messages: List<MessageDTO>,
    val isGroup: Boolean = false,
    val timeReceived: Date? = null,
    val timeRead: Date? = null,
)