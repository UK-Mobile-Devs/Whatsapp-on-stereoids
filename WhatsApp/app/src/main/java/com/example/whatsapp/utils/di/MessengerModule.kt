package com.example.whatsapp.utils.di

import com.example.firestorerepository.repositories.messenger.MessengerRepository
import com.example.firestorerepository.repositories.messenger.MessengerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface MessengerModule {

    @Binds
    fun getMessengerRepositoryImpl(repository: MessengerRepositoryImpl): MessengerRepository

}