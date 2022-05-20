package com.example.firestorerepository.datatypes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CallHistory (
    val uid : String? = "",
    val recipientUid : String?= null,
    val calls : List<Call> = emptyList()
) : Parcelable