package com.example.whatsapp.ui.fragments.home.chathistory

import com.example.firestorerepository.datatypes.Call
import com.example.firestorerepository.datatypes.CallHistory
import com.example.firestorerepository.repositories.chat.ChatRepositoryImpl
import com.example.whatsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatHistoryFragmentVM @Inject constructor(val chatRepo: ChatRepositoryImpl): BaseViewModel() {

    //region Variables

    private val bsCallHistory = BehaviorSubject.create<CallHistory>()

    private val psClickSendMessage = PublishSubject.create<Unit>()
    private val psClickRemoveCallLog = PublishSubject.create<Unit>()
    private val psClickBlockUser = PublishSubject.create<Unit>()

    private val contactName : Observable<String>
    private val contactAboutsMe : Observable<String>
    private val messageDate : Observable<String>

    //endregion

    init {
        contactName = bsCallHistory.flatMap {
            Observable.just("Bill Gates")
        }
        contactAboutsMe = bsCallHistory.flatMap {
            Observable.just("Available")
        }
        messageDate = bsCallHistory.flatMap {
            Observable.just("20 May 2022")
        }
    }


    //region Inputs
    fun setChatHistory(callHistory : CallHistory) {
        bsCallHistory.onNext(callHistory)
    }

    fun clickSendMessage() {
        psClickSendMessage.onNext(Unit)
    }

    fun clickRemoveCallLog() {
        psClickRemoveCallLog.onNext(Unit)
    }

    fun clickBlockUser() {
        psClickBlockUser.onNext(Unit)
    }

    fun getContactName() : Observable<String> {
        return contactName
    }

    fun getContactAboutMe() : Observable<String> {
        return contactAboutsMe
    }

    fun getMessageDate() : Observable<String> {
        return messageDate
    }

    //endregion


    //region Outputs
    fun getCalls() : Observable<List<Call>> {
        return bsCallHistory.map {
            it.calls
        }
    }

    fun blockUserClicked() : Observable<Unit> {
        return psClickBlockUser
    }

    fun removeCallLogClicked() : Observable<Unit>{
        return psClickRemoveCallLog
    }

    fun sendMessageClicked() : Observable<Unit> {
        return psClickSendMessage
    }
    //endregion
}