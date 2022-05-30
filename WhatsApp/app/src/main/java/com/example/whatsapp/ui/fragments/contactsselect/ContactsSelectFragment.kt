package com.example.whatsapp.ui.fragments.contactsselect

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentContactsSelectBinding
import com.example.whatsapp.utils.Constants
import com.example.whatsapp.utils.getSelectionFromTracker
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

@AndroidEntryPoint
class ContactsSelectFragment : BaseFragment<FragmentContactsSelectBinding>(), ActionMode.Callback {
    private val LOGTAG = "ACTIONSTEST"

    private val viewModel: ContactsSelectFragmentVM by viewModels()
    private val contactsSelectAdapter = ContactsSelectAdapter()
    private var tracker: SelectionTracker<Long>? = null
    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentContactsSelectBinding =
        FragmentContactsSelectBinding.inflate(layoutInflater)


    override fun initViews() {
        super.initViews()
        setHasOptionsMenu(true)

        binding.rvMessageContacts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = contactsSelectAdapter
        }

        tracker = SelectionTracker.Builder(
            CONTACTS_SELECTION_KEY,
            binding.rvMessageContacts,
            ContactsKeyProvider(binding.rvMessageContacts),
            ContactsDetailsLookup(binding.rvMessageContacts),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        tracker?.addObserver(object : SelectionTracker.SelectionObserver<Long>() {
            override fun onSelectionChanged() {

                if (actionMode == null) {
                    actionMode = activity?.startActionMode(this@ContactsSelectFragment)
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
        contactsSelectAdapter.tracker = tracker
    }

    override fun observeViewModel() {
        viewModel.getContacts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                contactsSelectAdapter.submitList(it)
            }
    }

    //region ActionMode.Callback
    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        mode.menuInflater.inflate(R.menu.home_chats_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
        menu.findItem(R.id.itemNewGroup).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu.findItem(R.id.itemNewBroadcast).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu.findItem(R.id.searchFragment).isVisible = false
        menu.findItem(R.id.itemLinkedDevices).isVisible = false
        menu.findItem(R.id.itemStarredMessages).isVisible = false
        menu.findItem(R.id.itemSettings).isVisible = false
        return true
    }

    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemNewGroup -> {
                // Navigate to NewGroup Fragment
                viewModel.selectNewGroup()
                tracker?.clearSelection()
                mode.finish()
                true
            }
            R.id.itemNewBroadcast -> {
                // Navigate to Broadcast Fragment
                viewModel.selectNewBroadcast()
                tracker?.clearSelection()
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
        Log.i("ACTIONTEST", "OptionsMenu Created")
        inflater.inflate(R.menu.contacts_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.searchFragment).isVisible = false
        menu.findItem(R.id.itemLinkedDevices).isVisible = false
        menu.findItem(R.id.itemStarredMessages).isVisible = false
        menu.findItem(R.id.itemSettings).isVisible = false
        menu.findItem(R.id.itemNewGroup).isVisible = false
        menu.findItem(R.id.itemNewBroadcast).isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                Log.i(LOGTAG, "SEARCH")
                true
            }
            R.id.inviteFriend -> {
                Log.i(LOGTAG, "INVITE")
                true
            }
            R.id.contacts -> {
                Log.i(LOGTAG, "CONTACTS")
                true
            }
            R.id.refresh -> {
                Log.i(LOGTAG, "REFRESH")
                true
            }
            R.id.help -> {
                Log.i(LOGTAG, "HELP")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    //endregion

    companion object {
        const val CONTACTS_SELECTION_KEY = "CONTACTS_SELECTION_KEY"

        fun newInstance(): ContactsSelectFragment {
            return ContactsSelectFragment()
        }
    }
}