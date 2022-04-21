package com.example.whatsapp.ui.fragments.messenger

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentHomeBinding
import com.example.whatsapp.databinding.FragmentMessengerBinding
import com.example.whatsapp.ui.fragments.home.HomeFragmentVM

abstract class BaseMessengerFragment<Binding : ViewBinding> : BaseFragment<Binding>() {

    private val viewModel by viewModels<MessengerFragmentVM>()




}