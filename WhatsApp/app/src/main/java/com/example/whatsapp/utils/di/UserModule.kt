package com.example.whatsapp.utils.di


import com.example.firestorerepository.repositories.UserRepository
import com.example.firestorerepository.repositories.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
interface UserModule {

    @Binds
    fun getUserRepositoryImpl(repository: UserRepositoryImpl): UserRepository

}