package com.example.whatsapp.utils.di

import com.example.whatsapp.base.BaseViewModel
import com.example.whatsapp.ui.fragments.contacts.ContactsFragmentVM
import com.example.whatsapp.ui.fragments.home.HomeFragmentVM
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindHomeVM(homeFragmentVM: HomeFragmentVM): BaseViewModel

    //@Binds
    //abstract fun bindContactsVM(contactsFragmentVM: ContactsFragmentVM): BaseViewModel

}