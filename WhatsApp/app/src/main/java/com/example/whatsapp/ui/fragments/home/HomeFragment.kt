package com.example.whatsapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    val viewModel : HomeFragmentVM by viewModels()

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentHomeBinding {
       return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
    }

    override fun observeViewModel() {

    }
}