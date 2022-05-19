package com.example.whatsapp.ui.fragments.home.calls

import android.view.ActionMode
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentCallsBinding
import com.example.whatsapp.ui.fragments.home.chats.*
import com.example.whatsapp.utils.getSelectionFromTracker
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class CallsFragment : BaseFragment<FragmentCallsBinding>(), ActionMode.Callback {

    //region Variables

    private val viewModel: CallsFragmentVM by viewModels()

    private val callsAdapter: CallsAdapter = CallsAdapter()

    private var tracker: SelectionTracker<Long>? = null

    private var actionMode: ActionMode? = null

    //endregion

    //region BaseFragment Overrides

    override fun observeViewModel() {

        viewModel.getListOfConversations()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                callsAdapter.submitList(it)
            }.autoDispose()
    }

    override fun initViews() {
        super.initViews()

        //region List Of Conversations RecyclerView Initialisation
        binding.rvCalls.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = callsAdapter
        }

        tracker = SelectionTracker.Builder(
            CALLS_SELECTION_KEY,
            binding.rvCalls,
            CallsKeyProvider(binding.rvCalls),
            CallsDetailsLookup(binding.rvCalls),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        tracker?.addObserver(object : SelectionTracker.SelectionObserver<Long>() {
            override fun onSelectionChanged() {

                if (actionMode == null) {
                    actionMode = activity?.startActionMode(this@CallsFragment)
                }

                tracker?.let { tracker ->


                    val selectionSize = tracker.selection.size()
                    if (selectionSize == 0) {
                        actionMode?.finish()
                        tracker.clearSelection()
                        return
                    }
                    actionMode?.title = "$selectionSize"
                }
            }
        })
        callsAdapter.tracker = tracker
        //endregion


    }

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentCallsBinding {
        return FragmentCallsBinding.inflate(layoutInflater)
    }

    //endregion

    //region ActionMode.Callback

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        mode.menuInflater.inflate(R.menu.home_calls_context_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
        return true
    }

    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.deleteChats -> {
                true
            }
            else -> false
        }
    }

    override fun onDestroyActionMode(actionMode: ActionMode) {
        this.actionMode = null
        actionMode.finish()
        tracker?.clearSelection()
    }
    //endregion

    //region Companion Object
    companion object {
        const val CALLS_SELECTION_KEY = "CALLS_SELECTION_KEY"

        fun newInstance(): CallsFragment {
            return CallsFragment()
        }
    }
    //endregion
}