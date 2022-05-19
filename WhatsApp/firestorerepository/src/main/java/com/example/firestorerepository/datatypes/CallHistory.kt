package com.example.firestorerepository.datatypes

data class CallHistory (
    val uid : String? = "",
    val recipientUid : String?= null,
    val calls : List<Call> = emptyList()
)