package com.example.whatsapp.ui.fragments.home.chats

import com.example.firestorerepository.datatypes.Conversation
import com.example.firestorerepository.repositories.chat.ChatRepository
import com.example.firestorerepository.repositories.chat.ChatRepositoryImpl
import com.example.whatsapp.base.BaseViewModel
import com.example.whatsapp.utils.conversationSelectionType
import com.example.whatsapp.utils.getSelectedItems
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class ChatsFragmentVM @Inject constructor(private val chatsRepo : ChatRepository) : BaseViewModel() {

    //region Variables
    private val psSelectionUpdated : PublishSubject<List<Long>> = PublishSubject.create()
    private val psPinChats : PublishSubject<Unit> = PublishSubject.create()
    private val psDeleteChats : PublishSubject<Unit> = PublishSubject.create()
    private val psMuteNotifications : PublishSubject<Unit> = PublishSubject.create()
    private val psArchiveMessages : PublishSubject<Unit> = PublishSubject.create()
    private val psExitGroup : PublishSubject<Unit> = PublishSubject.create()
    private val psExitGroups : PublishSubject<Unit> = PublishSubject.create()
    private val psAddChatShortcut : PublishSubject<Unit> = PublishSubject.create()
    private val psAddToContact : PublishSubject<Unit> = PublishSubject.create()
    private val psGroupInfo : PublishSubject<Unit> = PublishSubject.create()
    private val psMarkUnread : PublishSubject<Unit> = PublishSubject.create()
    private val psSelectAll : PublishSubject<Unit> = PublishSubject.create()

    private val obsConversationList = chatsRepo.getConversationList()
    private val obsSelectionType : Observable<ConversationSelectionType>
    private val obsSelectedItems : Observable<List<Conversation>>

    init {

        obsSelectionType = psSelectionUpdated.withLatestFrom(obsConversationList) { selection, conversations ->
            selection.conversationSelectionType(conversations)
        }

        obsSelectedItems = obsConversationList.withLatestFrom(psSelectionUpdated) { conversations, selection ->
            conversations.getSelectedItems(selection)
        }

        // Todo: Other stuff
    }

    //endregion

    //region Inputs

    fun updateSelectedItems(selectedItems : List<Long>) {
        psSelectionUpdated.onNext(selectedItems)
    }

    fun selectPinChats() {
        psPinChats.onNext(Unit)
    }

    fun selectDeleteChats() {
        psDeleteChats.onNext(Unit)
    }

    fun selectMuteNotifications() {
        psMuteNotifications.onNext(Unit)
    }

    fun selectArchiveMessages() {
        psArchiveMessages.onNext(Unit)
    }

    fun selectExitGroup() {
        psExitGroup.onNext(Unit)
    }

    fun selectExitGroups() {
        psExitGroups.onNext(Unit)
    }


    fun selectAddShortcut() {
        psAddChatShortcut.onNext(Unit)
    }

    fun selectAddToContact() {
        psAddToContact.onNext(Unit)
    }

    fun selectGroupInfo() {
        psGroupInfo.onNext(Unit)
    }

    fun selectMarkUnread() {
        psMarkUnread.onNext(Unit)
    }

    fun selectAll() {
        psSelectAll.onNext(Unit)
    }


    //endregion

    //region Outputs
    fun getChats() : Observable<List<Conversation>> {
        return obsConversationList
    }

    fun getSelectionType() : Observable<ConversationSelectionType> {
        return obsSelectionType
    }

    //endregion
}