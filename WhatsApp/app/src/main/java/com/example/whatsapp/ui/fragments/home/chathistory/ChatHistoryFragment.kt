package com.example.whatsapp.ui.fragments.home.chathistory

import android.view.ActionMode
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgument
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firestorerepository.datatypes.CallHistory
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentCallHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatHistoryFragment : BaseFragment<FragmentCallHistoryBinding>() {

    //region Variables

    private val viewModel: ChatHistoryFragmentVM by viewModels()

    private val chatHistoryAdapter: ChatHistoryAdapter = ChatHistoryAdapter()

    private val args: ChatHistoryFragmentArgs by navArgs()


    //endregion

    //region BaseFragment Overrides

    override fun observeViewModel() {


        chatHistoryAdapter.submitList(
            args.callHistory.calls
        )

        //region Inputs


        //endregion

        //region Outputs



        //endregion
    }

    override fun initViews() {
        super.initViews()
        setHasOptionsMenu(true)

        binding.rvContact.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatHistoryAdapter
        }


    }

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentCallHistoryBinding {
        return FragmentCallHistoryBinding.inflate(layoutInflater)
    }

    //endregion

    //region Options Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_call_history_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    //endregion

}
//endregion
