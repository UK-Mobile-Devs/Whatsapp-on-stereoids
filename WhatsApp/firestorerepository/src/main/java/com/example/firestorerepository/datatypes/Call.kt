package com.example.firestorerepository.datatypes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Call (
    val uid : String? = "",
    val isVideoCall : Boolean? = false,
    val isInBound : Boolean? = false
) : Parcelable