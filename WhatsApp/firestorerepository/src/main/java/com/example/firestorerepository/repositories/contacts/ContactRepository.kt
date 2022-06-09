package com.example.firestorerepository.repositories.contacts

import com.example.firestorerepository.datatypes.Contact
import io.reactivex.rxjava3.core.Observable

interface ContactRepository {
    fun getContacts(): Observable<List<Contact>>
    fun getContact(): Observable<Contact>
}