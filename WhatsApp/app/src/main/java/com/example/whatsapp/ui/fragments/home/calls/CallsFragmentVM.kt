package com.example.whatsapp.ui.fragments.home.calls

import com.example.firestorerepository.datatypes.CallHistory
import com.example.firestorerepository.repositories.UserRepositoryImpl
import com.example.firestorerepository.repositories.chat.ChatRepositoryImpl
import com.example.whatsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class CallsFragmentVM @Inject constructor(
    private val userRepo: UserRepositoryImpl,
    private val chatRepo : ChatRepositoryImpl
) : BaseViewModel() {

    private val psLaunchVideo: PublishSubject<String> = PublishSubject.create<String>()
    private val psLaunchCall = PublishSubject.create<String>()
    private val psViewCallHistory = PublishSubject.create<CallHistory>()


    init {

    }

    //region Inputs

    fun launchVideo(recipientUid : String) {
        psLaunchVideo.onNext(recipientUid)
    }

    fun launchCall(recipientUid : String) {
        psLaunchCall.onNext(recipientUid)
    }

    fun viewCallHistory(callHistory: CallHistory) {

    }

    //endregion


    //region Outputs

    fun getListOfConversations() : Observable<List<CallHistory>> {
        return chatRepo.getChatHistory()
    }

    fun launchCallHistory() : Observable<CallHistory> {
        return psViewCallHistory
    }

    //endregion

}