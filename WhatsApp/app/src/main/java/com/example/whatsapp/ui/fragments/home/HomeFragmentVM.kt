package com.example.whatsapp.ui.fragments.home

import com.example.firestorerepository.datatypes.User
import com.example.firestorerepository.repositories.UserRepositoryImpl
import com.example.whatsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


@HiltViewModel
class HomeFragmentVM @Inject constructor(
    private val userRepo: UserRepositoryImpl
) : BaseViewModel() {


    init {

    }

    //Todo: Remove this, it's just dummy data for now
    fun getUser(): Observable<User> {
        return userRepo.getUser()
    }


}