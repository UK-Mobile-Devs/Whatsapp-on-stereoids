package com.example.firestorerepository.repositories.contacts

import com.example.firestorerepository.datatypes.Contact
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepositoryImpl @Inject constructor() : ContactRepository {

    override fun getContacts(): Observable<List<Contact>> {
        return Observable.create<List<Contact>> { emitter ->
            val data = mutableListOf(
                Contact("1"),
                Contact("2"),
                Contact("3"),
                Contact("4"),
                Contact("5")
            )
            emitter.onNext(data)
        }.subscribeOn(Schedulers.io())
    }

    override fun getContact(): Observable<Contact> {
        return Observable.create {
            it.onNext(Contact("3"))
        }
    }

}