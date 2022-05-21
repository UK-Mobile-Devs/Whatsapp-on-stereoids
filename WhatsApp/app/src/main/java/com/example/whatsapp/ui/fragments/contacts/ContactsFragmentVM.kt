package com.example.whatsapp.ui.fragments.contacts

import androidx.lifecycle.ViewModel
import com.example.firestorerepository.repositories.contacts.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactsFragmentVM @Inject constructor(contactsRepo: ContactRepository) : ViewModel() {

    private val obsContacts = contactsRepo.getContacts()


    fun getContacts() = obsContacts

}