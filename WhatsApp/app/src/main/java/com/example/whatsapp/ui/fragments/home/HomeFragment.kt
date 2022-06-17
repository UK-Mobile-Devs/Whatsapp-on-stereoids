package com.example.whatsapp.ui.fragments.home

import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentHomeBinding
import com.example.whatsapp.ui.SelectionController
import com.example.whatsapp.ui.fragments.home.HomeStatePagerAdapter.Companion.CALLS_FRAGMENT_INDEX
import com.example.whatsapp.ui.fragments.home.HomeStatePagerAdapter.Companion.CAMERA_FRAGMENT_INDEX
import com.example.whatsapp.ui.fragments.home.HomeStatePagerAdapter.Companion.CHATS_FRAGMENT_INDEX
import com.example.whatsapp.ui.fragments.home.HomeStatePagerAdapter.Companion.STATUS_FRAGMENT_INDEX
import com.example.whatsapp.ui.fragments.home.calls.CallsFragment
import com.example.whatsapp.ui.fragments.home.camera.CameraFragment
import com.example.whatsapp.ui.fragments.home.chats.ChatsFragment
import com.example.whatsapp.ui.fragments.home.status.StatusFragment
import com.example.whatsapp.utils.Constants.fabActionKey
import com.example.whatsapp.utils.Constants.fabNavigateToCamera
import com.example.whatsapp.utils.Constants.fabNavigateToContactsFromCalls
import com.example.whatsapp.utils.Constants.fabNavigateToContactsFromChats
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val TAG = "HOMETEST"

    //region Variables
    private val viewModel by viewModels<HomeFragmentVM>()

    private lateinit var homeStatePagerAdapter: HomeStatePagerAdapter
    private var currentTabPosition = CHATS_FRAGMENT_INDEX
    private var lastTabPosition = currentTabPosition
    //endregion

    //region BaseFragment Overrides
    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        setHasOptionsMenu(true)
        //region Tabs Initialisation
        val fragments = listOf(
            CameraFragment.newInstance(),
            ChatsFragment.newInstance(),
            StatusFragment.newInstance(),
            CallsFragment.newInstance()
        )

        val selectionControllers = fragments.map { it as? SelectionController }

        homeStatePagerAdapter = HomeStatePagerAdapter(
            requireActivity(),
            fragments
        )
        binding.vpHomeScreen.adapter = homeStatePagerAdapter
        binding.vpHomeScreen.currentItem = CHATS_FRAGMENT_INDEX
        TabLayoutMediator(binding.tlNavigation, binding.vpHomeScreen) { currentTab, position ->
            when (position) {
                CHATS_FRAGMENT_INDEX -> getString(R.string.chats)
                STATUS_FRAGMENT_INDEX -> getString(R.string.status)
                CALLS_FRAGMENT_INDEX -> getString(R.string.calls)
                else -> null
            }?.let {
                currentTab.text = it
            }
            if (position == CAMERA_FRAGMENT_INDEX) {
                currentTab.icon =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_camera)
            }
        }.attach()

        binding.vpHomeScreen.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentTabPosition = position
                binding.fabAction.visibility = View.VISIBLE
                when (position) {
                    STATUS_FRAGMENT_INDEX -> binding.fabAction.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_camera,
                            null
                        )
                    )
                    CALLS_FRAGMENT_INDEX -> binding.fabAction.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_add_contact,
                            null
                        )
                    )
                    CHATS_FRAGMENT_INDEX -> binding.fabAction.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_messenger_icon,
                            null
                        )
                    )
                    CAMERA_FRAGMENT_INDEX -> binding.fabAction.visibility = View.GONE
                }

                selectionControllers[lastTabPosition]?.clear()
                lastTabPosition = currentTabPosition
            }
        })

        binding.fabAction.setOnClickListener {
            when (currentTabPosition) {
                CHATS_FRAGMENT_INDEX -> {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_contactsSelectFragment,
                        bundleOf(fabActionKey to fabNavigateToContactsFromChats)
                    )
                }
                STATUS_FRAGMENT_INDEX -> {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_cameraFragment,
                        bundleOf(fabActionKey to fabNavigateToCamera)
                    )

                }
                CALLS_FRAGMENT_INDEX -> {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_contactsSelectFragment,
                        bundleOf(fabActionKey to fabNavigateToContactsFromCalls)
                    )
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.i(TAG, "TAB POSITION $currentTabPosition")
        if (currentTabPosition == CHATS_FRAGMENT_INDEX) {
            inflater.inflate(R.menu.home_chats_menu, menu)
        } else if (currentTabPosition == STATUS_FRAGMENT_INDEX) {
            inflater.inflate(R.menu.home_status_menu, menu)
        } else if (currentTabPosition == CALLS_FRAGMENT_INDEX) {
            inflater.inflate(R.menu.home_calls_menu, menu)
        } else {
            activity?.invalidateOptionsMenu()
            menu.clear()
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun observeViewModel() {

    }
    //endregion
}