package com.example.firestorerepository.datatypes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Conversation(val uid : String, val isGroup : Boolean) : Parcelable
