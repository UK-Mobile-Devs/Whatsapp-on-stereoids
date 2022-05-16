package com.example.whatsapp.utils.di

import com.example.firestorerepository.repositories.chat.ChatRepository
import com.example.firestorerepository.repositories.chat.ChatRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ChatModule {

    @Binds
    fun getChatRepositoryImpl(repository: ChatRepositoryImpl): ChatRepository

}
