package com.example.firestorerepository.repositories

import com.example.firestorerepository.datatypes.User
import io.reactivex.rxjava3.core.Observable
import java.util.*
import javax.inject.Inject

interface UserRepository {
    fun getUser() : Observable<User>
}

class UserRepositoryImpl @Inject constructor(): UserRepository {

    init {

    }

    //region UserRepository

    override fun getUser(): Observable<User> {
        return Observable.just(User(UUID.randomUUID().toString()))
    }

    //endregion
}