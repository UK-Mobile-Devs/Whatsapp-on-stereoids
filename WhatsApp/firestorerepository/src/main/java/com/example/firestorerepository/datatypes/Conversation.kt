package com.example.firestorerepository.datatypes

data class Conversation(val uid : String)

data class Conversation(
    val uid: String,
    val messages: List<Message>,
    val timeReceived: Date? = null,
    val timeRead: Date? = null,
    val isGroup : Boolean
)