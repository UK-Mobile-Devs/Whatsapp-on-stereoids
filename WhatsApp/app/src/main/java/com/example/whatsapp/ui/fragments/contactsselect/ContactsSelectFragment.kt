package com.example.whatsapp.ui.fragments.contactsselect

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentContactsSelectBinding
import com.example.whatsapp.ui.fragments.contacts.ContactsAdapter
import com.example.whatsapp.ui.fragments.contacts.ContactsFragmentVM
import com.example.whatsapp.utils.Constants
import com.example.whatsapp.utils.getSelectionFromTracker
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

@AndroidEntryPoint
class ContactsSelectFragment : BaseFragment<FragmentContactsSelectBinding>(), ActionMode.Callback {

    private val viewModel: ContactsSelectFragmentVM by viewModels()
    private val contactsSelectAdapter = ContactsSelectAdapter()
    private var tracker: SelectionTracker<Long>? = null
    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentContactsSelectBinding =
        FragmentContactsSelectBinding.inflate(layoutInflater)


    override fun initViews() {
        super.initViews()

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
        inflater.inflate(R.menu.contacts_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                true
            }
            R.id.inviteFriend -> {
                true
            }
            R.id.contacts -> {
                true
            }
            R.id.refresh -> {
                true
            }
            R.id.help -> {
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