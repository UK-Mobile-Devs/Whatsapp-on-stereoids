package com.example.whatsapp.ui.fragments.home

import com.example.firestorerepository.datatypes.User
import com.example.firestorerepository.repositories.UserRepositoryImpl
import com.example.whatsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import java.util.*
import javax.inject.Inject

interface Inputs {

}

interface Outputs {
    fun getUser() : Observable<User>
}

@HiltViewModel
class HomeFragmentVM @Inject constructor(private val userRepository: UserRepositoryImpl): BaseViewModel(), Inputs, Outputs {

    val inputs : Inputs = this
    val outputs : Outputs = this

    init {

    }

    //region Inputs

    //endregion

    //region Outputs
    override fun getUser(): Observable<User> {
        return userRepository.getUser()
    }
    //endregion

}