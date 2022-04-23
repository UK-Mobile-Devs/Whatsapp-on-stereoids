package com.example.whatsapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentHomeBinding
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
            listOf(ChatsFragment.newInstance(), StatusFragment.newInstance(), CallsFragment.newInstance())
        )
        binding.vpHomeScreen.adapter = homeStatePagerAdapter
        TabLayoutMediator(binding.tlNavigation, binding.vpHomeScreen) { _, _ ->

        }
        //endregion

        binding.fabNewConversation.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_contactsFragment)
        }
    }


    override fun observeViewModel() {

    }
    //endregion

    //region Life-Cycle
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    //endregion

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.navigation, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    //endregion


}