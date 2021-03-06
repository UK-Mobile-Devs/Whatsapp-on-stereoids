package com.example.whatsapp.ui.fragments.contacts

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentContactsBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers


@AndroidEntryPoint
class ContactsFragment : BaseFragment<FragmentContactsBinding>() {

    private lateinit var navController: NavController
    private val viewModel: ContactsFragmentVM by viewModels()
    private val contactsAdapter = ContactsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun initViews() {
        super.initViews()
        binding.rvContacts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = contactsAdapter
        }
    }

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentContactsBinding =
        FragmentContactsBinding.inflate(layoutInflater)

    override fun observeViewModel() {
        viewModel.getContacts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                contactsAdapter.submitList(it)
            }
    }


}