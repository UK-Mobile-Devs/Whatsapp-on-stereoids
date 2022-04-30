package com.example.whatsapp.ui.fragments.home

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.example.whatsapp.ui.fragments.home.chats.ChatsFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    //region Variables
    private val viewModel by viewModels<HomeFragmentVM>()

    private lateinit var homeStatePagerAdapter : HomeStatePagerAdapter

    //endregion

    //region BaseFragment Overrides
    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        //region Tabs Initialisation
        homeStatePagerAdapter = HomeStatePagerAdapter(requireActivity(), listOf(ChatsFragment.newInstance()))
        binding.vpHomeScreen.adapter = homeStatePagerAdapter
        TabLayoutMediator(binding.tlNavigation, binding.vpHomeScreen) {_, _  ->

        }
        //endregion

        binding.fabNewConversation.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemSettings -> findNavController()!!.navigate(R.id.action_homeFragment_to_chatsFragment)

        }
        return super.onOptionsItemSelected(item)
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