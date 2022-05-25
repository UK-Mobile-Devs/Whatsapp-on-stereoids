package com.example.whatsapp.ui.fragments.contactsselect

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentContactsSelectBinding
import com.example.whatsapp.ui.fragments.contacts.ContactsAdapter
import com.example.whatsapp.ui.fragments.contacts.ContactsFragmentVM
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

@AndroidEntryPoint
class ContactsSelectFragment : BaseFragment<FragmentContactsSelectBinding>() {

    private val viewModel: ContactsFragmentVM by viewModels()
    private val contactsSelectAdapter = ContactsSelectAdapter()

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
}