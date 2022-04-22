package com.example.whatsapp.ui.fragments.messenger

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentMessengerBinding

abstract class BaseMessengerFragment : BaseFragment<FragmentMessengerBinding>() {

    val viewModel by viewModels<MessengerFragmentVM>()

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentMessengerBinding {
        return FragmentMessengerBinding.inflate(layoutInflater)
    }


}