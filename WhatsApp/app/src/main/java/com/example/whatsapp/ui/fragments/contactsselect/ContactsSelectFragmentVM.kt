package com.example.whatsapp.ui.fragments.contactsselect

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.firestorerepository.datatypes.Contact
import com.example.firestorerepository.repositories.contacts.ContactRepository
import com.example.whatsapp.utils.getSelectedItems
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class ContactsSelectFragmentVM @Inject constructor(contactsRepo: ContactRepository) : ViewModel() {

    private val TAG = "ContactsSelectVM"
    //region Variables
    private val psSelectionUpdated : PublishSubject<List<Long>> = PublishSubject.create()
    private val psNewGroup : PublishSubject<Unit> = PublishSubject.create()
    private val psNewBroadcast : PublishSubject<Unit> = PublishSubject.create()

    private val obsContactsList = contactsRepo.getContacts()
    private val obsSelectionType : Observable<ContactsSelectionType>
    private val obsSelectedItems : Observable<List<Contact>>

    init {

        obsSelectionType = psSelectionUpdated.withLatestFrom(obsContactsList) { selection, contacts ->
            selection.conversationSelectionType(contacts)
        }

        obsSelectedItems = obsContactsList.withLatestFrom(psSelectionUpdated) { contacts, selection ->
            contacts.getSelectedItems(selection)
        }

    }

    fun updateSelectedItems(selectedItems : List<Long>) {
        psSelectionUpdated.onNext(selectedItems)
        Log.i(TAG, "SELECTED ITEM: $selectedItems")
    }

    fun selectNewGroup() {
        psNewGroup.onNext(Unit)
    }

    fun selectNewBroadcast() {
        psNewBroadcast.onNext(Unit)
    }

    fun getContacts() = obsContactsList

    private fun List<Long>.conversationSelectionType(contacts : List<Contact>) : ContactsSelectionType {

        val selectedContacts = this.map {
            contacts[it.toInt()]
        }
        return if(selectedContacts.size > 1) {
            ContactsSelectionType.MULTIPLE_CONTACTS
        } else {
            ContactsSelectionType.SINGLE_CONTACT
        }
    }
}