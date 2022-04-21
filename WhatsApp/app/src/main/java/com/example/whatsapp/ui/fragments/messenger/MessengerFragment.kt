package com.example.whatsapp.ui.fragments.messenger

import android.view.LayoutInflater
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentHomeBinding
import com.example.whatsapp.databinding.FragmentMessengerBinding

class MessengerFragment : BaseFragment<FragmentMessengerBinding>() {

    //region Variables



    //endregion

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentMessengerBinding {
        return FragmentMessengerBinding.inflate(layoutInflater)
    }

    override fun observeViewModel() {

    }


}