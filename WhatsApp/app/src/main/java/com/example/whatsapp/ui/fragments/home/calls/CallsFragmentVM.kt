package com.example.whatsapp.ui.fragments.home.calls

import com.example.firestorerepository.datatypes.CallHistory
import com.example.firestorerepository.repositories.UserRepositoryImpl
import com.example.firestorerepository.repositories.chat.ChatRepositoryImpl
import com.example.whatsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@HiltViewModel
class CallsFragmentVM @Inject constructor(
    private val userRepo: UserRepositoryImpl,
    private val chatRepo : ChatRepositoryImpl
) : BaseViewModel() {




    init {

    }

    //region Inputs


    //endregion


    //region Outputs

    fun getListOfConversations() : Observable<List<CallHistory>> {
        return chatRepo.getChatHistory()
    }

    //endregion

}