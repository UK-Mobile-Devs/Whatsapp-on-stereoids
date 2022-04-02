package com.example.whatsapp.utils.di

import com.example.firestorerepository.repositories.IUserRepository
import com.example.firestorerepository.repositories.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
interface UserModule {

    @Provides
    @Binds
    fun getUserRepositoryImpl(repository: UserRepositoryImpl): UserRepositoryImpl = repository

}