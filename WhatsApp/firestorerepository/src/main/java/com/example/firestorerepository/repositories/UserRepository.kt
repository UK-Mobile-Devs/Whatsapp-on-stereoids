package com.example.firestorerepository.repositories

import com.example.firestorerepository.datatypes.User
import io.reactivex.rxjava3.core.Observable

interface UserRepository {
    fun getUser(): Observable<User>
}