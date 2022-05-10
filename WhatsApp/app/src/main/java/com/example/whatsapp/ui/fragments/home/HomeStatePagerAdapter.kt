package com.example.whatsapp.ui.fragments.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeStatePagerAdapter(fragmentManager: FragmentActivity, private val listOfFragments : List<Fragment>) :
    FragmentStateAdapter(fragmentManager) {

    override fun getItemCount(): Int {
        return listOfFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return listOfFragments[position]
    }

    companion object{
        const val CAMERA_FRAGMENT_INDEX = 0
        const val CHATS_FRAGMENT_INDEX = 1
    }

}