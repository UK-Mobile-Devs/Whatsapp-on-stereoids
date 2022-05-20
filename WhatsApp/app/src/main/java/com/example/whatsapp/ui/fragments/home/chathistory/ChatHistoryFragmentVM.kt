package com.example.whatsapp.ui.fragments.home.chathistory

import com.example.firestorerepository.datatypes.Call
import com.example.firestorerepository.datatypes.CallHistory
import com.example.firestorerepository.repositories.chat.ChatRepositoryImpl
import com.example.whatsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class ChatHistoryFragmentVM @Inject constructor(val chatRepo: ChatRepositoryImpl): BaseViewModel() {

    //region Variables

    private val bsCallHistory = BehaviorSubject.create<CallHistory>()

    //endregion


    //region Inputs
    fun setChatHistory(callHistory : CallHistory) {
        bsCallHistory.onNext(callHistory)
    }
    //endregion


    //region Outputs
    fun getCals() : Observable<List<Call>> {
        return bsCallHistory.map {
            it.calls
        }
    }
    //endregion
}