package com.example.whatsapp.ui.fragments.settings.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentNotificationsBinding
import com.example.whatsapp.databinding.FragmentSettingsChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsChatsFragment : BaseFragment<FragmentSettingsChatBinding>() {


    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentSettingsChatBinding {
        return FragmentSettingsChatBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        observeUiInteractions()
        setHasOptionsMenu(true)
    }

    private fun observeUiInteractions() {
        binding.themeTransfer.setOnClickListener {

        }

        binding.wallpaperTransfer.setOnClickListener {

        }

        binding.enterIsSend.setOnClickListener {

        }

        binding.mediaVisibility.setOnClickListener {

        }

        binding.fontSizeLayout.setOnClickListener {

        }

        binding.archivedChatsLayout.setOnClickListener {

        }
        binding.languageLayout.setOnClickListener {

        }
        binding.backupLayout.setOnClickListener {

        }
        binding.chatHistoryLayout.setOnClickListener {

        }
    }

    override fun observeViewModel() {

    }
}
