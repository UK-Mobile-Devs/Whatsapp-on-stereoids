package com.example.whatsapp.ui.fragments.settings.account

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentAccountsBinding
import com.example.whatsapp.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountsFragment  : BaseFragment<FragmentAccountsBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun initViews() {
        super.initViews()

        binding.privacyTransfer.setOnClickListener {
            findNavController().navigate(R.id.action_accountsFragment_to_privacyFragment)
        }
        binding.securityTransferLayout.setOnClickListener {
            findNavController().navigate(R.id.action_accountsFragment_to_securityFragment)
        }
        binding.twofaLayout.setOnClickListener {
            findNavController().navigate(R.id.action_accountsFragment_to_verificationFragment)
        }
        binding.changeNumberLayout.setOnClickListener {
            findNavController().navigate(R.id.action_accountsFragment_to_changeNumberFragment)
        }
        binding.requestLayout.setOnClickListener {
            findNavController().navigate(R.id.action_accountsFragment_to_accountInformationFragment)
        }
        binding.deleteAccountLayout.setOnClickListener {
            findNavController().navigate(R.id.action_accountsFragment_to_deleteAccountFragment)
        }
    }
    //region BaseFragment Overrides
    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentAccountsBinding {
        return FragmentAccountsBinding.inflate(layoutInflater)
    }
    override fun observeViewModel() {

    }
}