package com.example.whatsapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
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
import kotlinx.android.synthetic.main.fragment_home.*

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
        setTabWidthAsWrapContent(0)
        //region Tabs Initialisation
        homeStatePagerAdapter = HomeStatePagerAdapter(requireActivity(), listOf(ChatsFragment.newInstance()))
        binding.vpHomeScreen.adapter = homeStatePagerAdapter
        TabLayoutMediator(binding.tlNavigation, binding.vpHomeScreen) {_, _  ->

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

    private fun setTabWidthAsWrapContent(tabPosition: Int) {
        val layout = (binding.tabParent.getChildAt(0) as LinearLayout).getChildAt(tabPosition) as LinearLayout
        val layoutParams = layout.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = 0f
        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        layout.layoutParams = layoutParams
    }

}