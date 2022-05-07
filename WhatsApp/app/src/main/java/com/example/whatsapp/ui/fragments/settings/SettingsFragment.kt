package com.example.whatsapp.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentSettingsBinding
import com.example.whatsapp.utils.Constants.SELECT_PROFILE_IMAGE_KEY
import com.example.whatsapp.utils.Constants.SETTINGS_FRAGMENT_KEY
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        initializeLifecycleObserver(SETTINGS_FRAGMENT_KEY)
        super.onCreate(savedInstanceState)
    }

    override fun initViews() {
        super.initViews()
        binding.ivProfilePicture.setOnClickListener {
            observer.selectImage()
        }

        binding.layoutAccountRow.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_accountsFragment)
        }
        binding.layoutChatsRow.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_chatsFragment)
        }
        binding.layoutNotificationsRow.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_notificationsFragment)
        }
        binding.layoutStorageRow.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_storageFragment)
        }
        binding.layoutHelpRow.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_helpFragment)
        }
        binding.layoutReferralRow.setOnClickListener {
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