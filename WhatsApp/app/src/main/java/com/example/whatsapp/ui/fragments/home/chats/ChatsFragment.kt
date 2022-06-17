package com.example.whatsapp.ui.fragments.home.chats

import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.LayoutInflater
import android.view.MenuInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentChatsBinding
import com.example.whatsapp.ui.SelectionController
import com.example.whatsapp.utils.getSelectionFromTracker
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


@AndroidEntryPoint
class ChatsFragment : BaseFragment<FragmentChatsBinding>(), ActionMode.Callback, SelectionController {

    //region Variables

    private val viewModel: ChatsFragmentVM by viewModels()

    private val chatsAdapter: ChatsAdapter = ChatsAdapter()

    private var tracker: SelectionTracker<Long>? = null

    private var actionMode: ActionMode? = null

    //endregion

    //region BaseFragment Overrides
    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentChatsBinding {
        return FragmentChatsBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        setHasOptionsMenu(true)

        //region List Of Conversations RecyclerView Initialisation
        binding.rvChats.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatsAdapter
        }

        tracker = SelectionTracker.Builder(
            CHAT_SELECTION_KEY,
            binding.rvChats,
            ChatsKeyProvider(binding.rvChats),
            ChatDetailsLookup(binding.rvChats),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        tracker?.addObserver(object : SelectionTracker.SelectionObserver<Long>() {
            override fun onSelectionChanged() {

                if (actionMode == null) {
                    actionMode = activity?.startActionMode(this@ChatsFragment)
                }

                tracker?.let { tracker ->

                    viewModel.updateSelectedItems(tracker.getSelectionFromTracker())

                    val selectionSize = tracker.selection.size()
                    if (selectionSize == 0) {
                        actionMode?.finish()
                        tracker.clearSelection()
                        return
                    }
                    actionMode?.title = "$selectionSize"
                }
            }
        })
        chatsAdapter.tracker = tracker
        //endregion
    }

    override fun initArgs(arguments: Bundle) {
        super.initArgs(arguments)
        tracker?.onRestoreInstanceState(arguments)
    }

    override fun observeViewModel() {

        //region Inputs

        //endregion

        //region Outputs

        viewModel.getChats()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                chatsAdapter.submitList(it)
            }.autoDispose()

        viewModel.getSelectionType()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                updateActionBarState(it)
            }

        //endregion
    }
    //endregion

    //region ActionMode.Callback
    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        mode.menuInflater.inflate(R.menu.home_chats_context_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
        menu.findItem(R.id.pinChats).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu.findItem(R.id.deleteChats).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu.findItem(R.id.archiveMessages).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu.findItem(R.id.muteNotifications).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return true
    }

    private fun disableAllActionBarItems() {
        actionMode?.menu?.findItem(R.id.pinChats)?.isVisible = false
        actionMode?.menu?.findItem(R.id.deleteChats)?.isVisible = false
        actionMode?.menu?.findItem(R.id.muteNotifications)?.isVisible = false
        actionMode?.menu?.findItem(R.id.archiveMessages)?.isVisible = false
        actionMode?.menu?.findItem(R.id.exitGroup)?.isVisible = false
        actionMode?.menu?.findItem(R.id.addChatShortcut)?.isVisible = false
        actionMode?.menu?.findItem(R.id.viewContact)?.isVisible = false
        actionMode?.menu?.findItem(R.id.addToContact)?.isVisible = false
        actionMode?.menu?.findItem(R.id.viewContact)?.isVisible = false
        actionMode?.menu?.findItem(R.id.exitGroups)?.isVisible = false
        actionMode?.menu?.findItem(R.id.addToContact)?.isVisible = false
        actionMode?.menu?.findItem(R.id.groupInfo)?.isVisible = false
        actionMode?.menu?.findItem(R.id.markUnread)?.isVisible = false
        actionMode?.menu?.findItem(R.id.selectAll)?.isVisible = false
    }

    private fun updateActionBarState(selectionType: ConversationSelectionType) {
        when (selectionType) {
            ConversationSelectionType.SINGLE_GROUP -> {
                disableAllActionBarItems()
                actionMode?.menu?.findItem(R.id.pinChats)?.isVisible = true
                actionMode?.menu?.findItem(R.id.muteNotifications)?.isVisible = true
                actionMode?.menu?.findItem(R.id.archiveMessages)?.isVisible = true
                actionMode?.menu?.findItem(R.id.deleteChats)?.isVisible = false
                actionMode?.menu?.findItem(R.id.exitGroup)?.isVisible = true
                actionMode?.menu?.findItem(R.id.addChatShortcut)?.isVisible = true
                actionMode?.menu?.findItem(R.id.groupInfo)?.isVisible = true
                actionMode?.menu?.findItem(R.id.markUnread)?.isVisible = true
                actionMode?.menu?.findItem(R.id.selectAll)?.isVisible = true
            }
            ConversationSelectionType.SINGLE_DIRECT_MESSAGE -> {
                disableAllActionBarItems()
                actionMode?.menu?.findItem(R.id.deleteChats)?.isVisible = true
                actionMode?.menu?.findItem(R.id.pinChats)?.isVisible = true
                actionMode?.menu?.findItem(R.id.muteNotifications)?.isVisible = true
                actionMode?.menu?.findItem(R.id.archiveMessages)?.isVisible = true
                actionMode?.menu?.findItem(R.id.addChatShortcut)?.isVisible = true
                actionMode?.menu?.findItem(R.id.viewContact)?.isVisible = true
                actionMode?.menu?.findItem(R.id.markUnread)?.isVisible = true
                actionMode?.menu?.findItem(R.id.selectAll)?.isVisible = true
            }
            ConversationSelectionType.MIXTURE -> {
                disableAllActionBarItems()
                actionMode?.menu?.findItem(R.id.pinChats)?.isVisible = true
                actionMode?.menu?.findItem(R.id.muteNotifications)?.isVisible = true
                actionMode?.menu?.findItem(R.id.archiveMessages)?.isVisible = true
                actionMode?.menu?.findItem(R.id.markUnread)?.isVisible = true
                actionMode?.menu?.findItem(R.id.selectAll)?.isVisible = true
            }
            ConversationSelectionType.MULTIPLE_GROUPS -> {
                disableAllActionBarItems()
                actionMode?.menu?.findItem(R.id.pinChats)?.isVisible = true
                actionMode?.menu?.findItem(R.id.muteNotifications)?.isVisible = true
                actionMode?.menu?.findItem(R.id.archiveMessages)?.isVisible = true
                actionMode?.menu?.findItem(R.id.deleteChats)?.isVisible = false
                actionMode?.menu?.findItem(R.id.exitGroups)?.isVisible = true
                actionMode?.menu?.findItem(R.id.markUnread)?.isVisible = true
                actionMode?.menu?.findItem(R.id.selectAll)?.isVisible = true
            }
            ConversationSelectionType.MULTIPLE_DIRECT_MESSAGE -> {
                disableAllActionBarItems()
                actionMode?.menu?.findItem(R.id.deleteChats)?.isVisible = true
                actionMode?.menu?.findItem(R.id.pinChats)?.isVisible = true
                actionMode?.menu?.findItem(R.id.muteNotifications)?.isVisible = true
                actionMode?.menu?.findItem(R.id.archiveMessages)?.isVisible = true
                actionMode?.menu?.findItem(R.id.markUnread)?.isVisible = true
                actionMode?.menu?.findItem(R.id.selectAll)?.isVisible = true
            }
        }
    }

    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.pinChats -> {
                viewModel.selectPinChats()
                tracker?.clearSelection()
                mode.finish()
                true
            }
            R.id.deleteChats -> {
                viewModel.selectDeleteChats()
                tracker?.clearSelection()
                mode.finish()
                true
            }
            R.id.muteNotifications -> {
                viewModel.selectMuteNotifications()
                tracker?.clearSelection()
                mode.finish()
                true
            }
            R.id.archiveMessages -> {
                viewModel.selectArchiveMessages()
                tracker?.clearSelection()
                mode.finish()
                true
            }
            R.id.exitGroup -> {
                viewModel.selectExitGroup()
                tracker?.clearSelection()
                mode.finish()
                true
            }
            R.id.exitGroups -> {
                viewModel.selectExitGroups()
                mode.finish()
                true
            }
            R.id.addChatShortcut -> {
                viewModel.selectAddShortcut()
                tracker?.clearSelection()
                mode.finish()
                true
            }
            R.id.addToContact -> {
                viewModel.selectAddToContact()
                tracker?.clearSelection()
                mode.finish()
                true
            }
            R.id.groupInfo -> {
                viewModel.selectGroupInfo()
                tracker?.clearSelection()
                mode.finish()
                true
            }
            R.id.markUnread -> {
                viewModel.selectMarkUnread()
                tracker?.clearSelection()
                mode.finish()
                true
            }
            R.id.selectAll -> {
                viewModel.selectAll()
                mode.finish()
                true
            }
            else -> {
                tracker?.clearSelection()
                mode.finish()
                false
            }
        }
    }

    override fun onDestroyActionMode(actionMode: ActionMode) {
        this.actionMode = null
        actionMode.finish()
        tracker?.clearSelection()
    }
    //endregion

    //region Fragment Life-Cycle
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        tracker?.onSaveInstanceState(outState)
    }
    //endregion


    //region Options Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_chats_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemSettings -> {
                findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    //endregion

    override fun clear() {
        tracker?.clearSelection()
    }

    //region Companion Object
    companion object {
        const val CHAT_SELECTION_KEY = "CHAT_SELECTION_KEY"

        fun newInstance(): ChatsFragment {
            return ChatsFragment()
        }
    }
    //endregion
}