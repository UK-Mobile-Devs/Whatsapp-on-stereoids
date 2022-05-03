package com.example.whatsapp.ui.fragments.settings

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun initViews() {
        super.initViews()
        binding.displayPhoto.setOnClickListener {
            val i = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )
            val ACTIVITY_SELECT_IMAGE = 1234
            startActivityForResult(i, ACTIVITY_SELECT_IMAGE)
        }

        binding.accountTransfer.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_accountsFragment)
        }
        binding.chatsTransfer.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_chatsFragment)
        }
        binding.notificationTransfer.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_notificationsFragment)
        }
        binding.storageTransfer.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_storageFragment)
        }
        binding.helpTransfer.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_helpFragment)
        }
        binding.referralTransfer.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_referralFragment)
        }
    }

    //region BaseFragment Overrides
    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(layoutInflater)
    }
    override fun observeViewModel() {

    }
}