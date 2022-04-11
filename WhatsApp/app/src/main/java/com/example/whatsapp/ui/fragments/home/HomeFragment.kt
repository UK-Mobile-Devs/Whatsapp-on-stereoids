package com.example.whatsapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeFragmentVM>()


    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        binding.fabNewConversation.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_contactsFragment)
        }

        binding.tabParent.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                navigateToTab(tab)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //no-op
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
                //no-op
            }
        })
    }


    override fun observeViewModel() {
        viewModel.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.tvBody.text = it.uid
            }.autoDispose()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTabWidthAsWrapContent(0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.navigation, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun navigateToTab(tab: TabLayout.Tab){
        when(tab){
            tabCamera -> ""
            tabCalls -> ""
            tabChats -> ""
            tabStatus -> ""
        }
    }

    private fun setTabWidthAsWrapContent(tabPosition: Int) {
        val layout = (binding.tabParent.getChildAt(0) as LinearLayout).getChildAt(tabPosition) as LinearLayout
        val layoutParams = layout.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = 0f
        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        layout.layoutParams = layoutParams
    }

}