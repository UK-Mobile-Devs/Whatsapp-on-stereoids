package com.example.whatsapp.ui.fragments.home.chathistory

import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgument
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firestorerepository.datatypes.CallHistory
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentCallHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class ChatHistoryFragment : BaseFragment<FragmentCallHistoryBinding>() {

    //region Variables

    private val viewModel: ChatHistoryFragmentVM by viewModels()

    private val chatHistoryAdapter: ChatHistoryAdapter = ChatHistoryAdapter()

    private val args: ChatHistoryFragmentArgs by navArgs()


    //endregion

    //region BaseFragment Overrides

    override fun observeViewModel() {

        //region Inputs

        viewModel.setChatHistory(args.callHistory)

        //endregion

        //region Outputs

        viewModel.getContactAboutMe()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.tvAbout.text = it
            }.autoDispose()

        viewModel.getContactName()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.tvName.text = it
            }.autoDispose()

        viewModel.getMessageDate()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.tvDateTime.text = it
            }.autoDispose()

        viewModel.getCalls().subscribe {
            chatHistoryAdapter.submitList(it)
        }.autoDispose()

        viewModel.blockUserClicked().subscribe {
            // Todo: Add block user logic here
        }.autoDispose()

        viewModel.removeCallLogClicked().subscribe {
            // Todo: Add remove call logic logic here
        }.autoDispose()

        viewModel.sendMessageClicked().subscribe {
            // Todo: Send message logic here
        }.autoDispose()

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sendMessage -> {
                viewModel.clickSendMessage()
                true
            }
            R.id.removeCallLog -> {
                viewModel.clickRemoveCallLog()
                true
            }
            R.id.blockUser -> {
                viewModel.clickBlockUser()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
    //endregion
}
//endregion
