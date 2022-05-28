package com.example.whatsapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentHomeBinding
import com.example.whatsapp.ui.fragments.home.HomeStatePagerAdapter.Companion.CALLS_FRAGMENT_INDEX
import com.example.whatsapp.ui.fragments.home.HomeStatePagerAdapter.Companion.CAMERA_FRAGMENT_INDEX
import com.example.whatsapp.ui.fragments.home.HomeStatePagerAdapter.Companion.CHATS_FRAGMENT_INDEX
import com.example.whatsapp.ui.fragments.home.HomeStatePagerAdapter.Companion.STATUS_FRAGMENT_INDEX
import com.example.whatsapp.ui.fragments.home.calls.CallsFragment
import com.example.whatsapp.ui.fragments.home.camera.CameraFragment
import com.example.whatsapp.ui.fragments.home.chats.ChatsFragment
import com.example.whatsapp.ui.fragments.home.status.StatusFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    //region Variables
    private val viewModel by viewModels<HomeFragmentVM>()

    private lateinit var homeStatePagerAdapter: HomeStatePagerAdapter

    //endregion

    //region BaseFragment Overrides
    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        //region Tabs Initialisation
        homeStatePagerAdapter = HomeStatePagerAdapter(
            requireActivity(),
            listOf(
                CameraFragment.newInstance(),
                ChatsFragment.newInstance(),
                StatusFragment.newInstance(),
                CallsFragment.newInstance()
            )
        )
        binding.vpHomeScreen.adapter = homeStatePagerAdapter
        binding.vpHomeScreen.currentItem = CHATS_FRAGMENT_INDEX
        TabLayoutMediator(binding.tlNavigation, binding.vpHomeScreen) { currentTab, position ->
            if(position == CAMERA_FRAGMENT_INDEX) {
                currentTab.icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_camera)
            }
            when (position) {
                CHATS_FRAGMENT_INDEX -> getString(R.string.chats)
                STATUS_FRAGMENT_INDEX -> getString(R.string.status)
                CALLS_FRAGMENT_INDEX -> getString(R.string.calls)
                else -> null
            }?.let {
                currentTab.text = it
            }
        }.attach()
        //endregion

        binding.fabNewConversation.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_contactsFragment)
        }
    }

    override fun observeViewModel() {

    }
    //endregion
}