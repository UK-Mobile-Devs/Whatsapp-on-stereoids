package com.example.firestorerepository.repositories

import com.example.firestorerepository.datatypes.User
import io.reactivex.rxjava3.core.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepositoryImpl @Inject constructor() : UserRepository {

    init {

    }

    override fun getUser(): Observable<User> {
        return Observable.just(User(UUID.randomUUID().toString()))
    }

}