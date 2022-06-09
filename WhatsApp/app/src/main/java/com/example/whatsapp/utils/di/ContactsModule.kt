package com.example.whatsapp.utils.di

import com.example.firestorerepository.repositories.contacts.ContactRepository
import com.example.firestorerepository.repositories.contacts.ContactRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ContactsModule {

    @Binds
    fun getContactsRepositoryModule(repo: ContactRepositoryImpl): ContactRepository

}