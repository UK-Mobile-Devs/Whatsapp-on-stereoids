package com.example.whatsapp.ui.fragments.contactsselect

import android.os.Bundle
import android.view.ActionMode
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentContactsSelectBinding
import com.example.whatsapp.ui.fragments.contacts.ContactsAdapter
import com.example.whatsapp.ui.fragments.contacts.ContactsFragmentVM
import com.example.whatsapp.utils.getSelectionFromTracker
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

@AndroidEntryPoint
class ContactsSelectFragment : BaseFragment<FragmentContactsSelectBinding>() {

    private val viewModel: ContactsSelectFragmentVM by viewModels()
    private val contactsSelectAdapter = ContactsSelectAdapter()
    private var tracker: SelectionTracker<Long>? = null
    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

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

//                if (actionMode == null) {
//                    actionMode = activity?.startActionMode(this@ContactsSelectFragment)
//                }

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

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentContactsSelectBinding =
        FragmentContactsSelectBinding.inflate(layoutInflater)

    override fun observeViewModel() {
        viewModel.getContacts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                contactsSelectAdapter.submitList(it)
            }
    }
    companion object {
        const val CONTACTS_SELECTION_KEY = "CONTACTS_SELECTION_KEY"

        fun newInstance(): ContactsSelectFragment {
            return ContactsSelectFragment()
        }
    }
}