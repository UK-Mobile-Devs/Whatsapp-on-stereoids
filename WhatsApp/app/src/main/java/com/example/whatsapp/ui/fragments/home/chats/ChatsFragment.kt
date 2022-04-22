package com.example.whatsapp.ui.fragments.home.chats

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentChatsBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class ChatsFragment : BaseFragment<FragmentChatsBinding>() {

    //region Variables
    private val viewModel: ChatsFragmentVM by viewModels()

    private val chatsAdapter: ChatsAdapter = ChatsAdapter()

    private var tracker: SelectionTracker<Long>? = null

    //endregion

    //region BaseFragment Overrides
    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentChatsBinding {
        return FragmentChatsBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()

        //region List Of Conversations RecyclerView Initialisation
        binding.rvChats.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatsAdapter
        }

        this.findNavController()

        tracker = SelectionTracker.Builder(
            CHAT_SELECTION_KEY,
            binding.rvChats,
            StableIdKeyProvider(binding.rvChats),
            ItemDetailsLookup(binding.rvChats),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        chatsAdapter.tracker = tracker

        //endregion
    }

    override fun observeViewModel() {

        //region Inputs

        //endregion

        //region Outputs

        viewModel.getChats()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                chatsAdapter.submitList(it)
            }.autoDispose()

        //endregion
    }
    //endregion

    //region Companion Object
    companion object {
        const val CHAT_SELECTION_KEY = "CHAT_SELECTION_KEY"

        fun newInstance() : ChatsFragment {
            return ChatsFragment()
        }
    }
    //endregion

}