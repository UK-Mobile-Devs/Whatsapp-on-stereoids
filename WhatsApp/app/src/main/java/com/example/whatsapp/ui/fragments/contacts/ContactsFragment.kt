package com.example.whatsapp.ui.fragments.contacts

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentContactsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContactsFragment : BaseFragment<FragmentContactsBinding>() {

    private lateinit var navController: NavController
    private val viewModel: ContactsFragmentVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun initViews() {
        super.initViews()
        binding.rvContacts.apply {

        }
    }

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentContactsBinding =
        FragmentContactsBinding.inflate(layoutInflater)

    override fun observeViewModel() {

    }


}