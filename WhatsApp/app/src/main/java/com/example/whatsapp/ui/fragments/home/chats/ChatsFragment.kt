package com.example.whatsapp.ui.fragments.home.chats

import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentChatsBinding

class ChatsFragment : BaseFragment<FragmentChatsBinding>() {

    private val chatsAdapter : ChatsAdapter by lazy {
        ChatsAdapter()
    }

    //region BaseFragment Overrides
    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentChatsBinding {
        return FragmentChatsBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()

        binding.rvChats.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatsAdapter
        }

    }

    override fun observeViewModel() {

    }
    //endregion

}