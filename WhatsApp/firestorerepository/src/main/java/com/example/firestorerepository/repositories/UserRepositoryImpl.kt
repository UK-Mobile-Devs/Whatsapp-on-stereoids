package com.example.firestorerepository.repositories

import android.content.Context
import com.example.firestorerepository.datatypes.User
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

interface IUserRepository {
    fun getUser() : Observable<User>
}

class UserRepositoryImpl @Inject constructor(): IUserRepository {

    init {

    }

    //region UserRepository

    override fun getUser(): Observable<User> {
        return Observable.just(User(UUID.randomUUID().toString()))
    }

    //endregion
}


@InstallIn(ViewModelComponent::class)
@Module
abstract class BindUserRepository {

    @Binds
    abstract fun bindUserRepository(repo: UserRepositoryImpl) : IUserRepository

}