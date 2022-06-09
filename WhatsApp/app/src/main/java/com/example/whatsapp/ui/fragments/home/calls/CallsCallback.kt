package com.example.whatsapp.ui.fragments.home.calls

import com.example.firestorerepository.datatypes.CallHistory

interface CallsCallback {
    fun startCallIntent(recipientUid : String)
    fun startVideoIntent(recipientUid: String)
    fun viewCallHistory(callHistory: CallHistory)
}