package com.example.firestorerepository.datatypes

import java.util.*

data class Conversation(
    val uid: String,
    val messages: List<Message>,
    val timeReceived: Date? = null,
    val timeRead: Date? = null
)