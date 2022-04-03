package com.example.firestorerepository.repositories

import com.example.firestorerepository.datatypes.User
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

interface IUserRepository {
    fun getUser() : Observable<User>
}

@Singleton
class UserRepositoryImpl @Inject constructor(): IUserRepository {

    init {

    }

    //region IUserRepository

    override fun getUser(): Observable<User> {
        return Observable.just(User(UUID.randomUUID().toString()))
    }

    //endregion
}