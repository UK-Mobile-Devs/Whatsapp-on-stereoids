package com.example.whatsapp.ui.fragments.home.calls

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firestorerepository.datatypes.CallHistory
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentCallsBinding
import com.example.whatsapp.ui.SelectionController
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class CallsFragment : BaseFragment<FragmentCallsBinding>(), ActionMode.Callback, CallsCallback, SelectionController {

    //region Variables

    private val viewModel: CallsFragmentVM by viewModels()

    private val callsAdapter: CallsAdapter by lazy {
        CallsAdapter(this)
    }

    private var tracker: SelectionTracker<Long>? = null

    private var actionMode: ActionMode? = null

    //endregion

    //region BaseFragment Overrides

    override fun observeViewModel() {

        //region Inputs
        viewModel.getListOfConversations()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                callsAdapter.submitList(it)
            }.autoDispose()

        viewModel.launchCallHistory()
            .subscribe {
                val bundle = Bundle().apply {
                    putParcelable(CALL_HISTORY_KEY, it)
                }
                findNavController().navigate(R.id.action_home_to_callHistoryFragment, bundle)
            }.autoDispose()

        //endregion

        //region Outputs

        //endregion
    }

    override fun initViews() {
        super.initViews()
        setHasOptionsMenu(true)

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

    //region Options Menu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.search -> {
                viewModel.searchClick()
                Log.i("CALLTEST", "Search Clicked!")
                true
            }
            R.id.clearCallLog -> {
                viewModel.clearCallLogClick()
                Log.i("CALLTEST", "Clear Clicked!")
                true
            }
            R.id.settings -> {
                viewModel.settingsClick()
                Log.i("CALLTEST", "Settings Clicked!")
                true
            }
            else ->  super.onOptionsItemSelected(item)
        }
    }
    //endregion

    //region ActionMode.Callback

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        mode.menuInflater.inflate(R.menu.call_history_menu, menu)
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

    //region CallsCallback
    override fun startCallIntent(recipientUid: String) {
        viewModel.launchCall(recipientUid)
    }

    override fun startVideoIntent(recipientUid: String) {
        viewModel.launchVideo(recipientUid)
    }

    override fun viewCallHistory(callHistory: CallHistory) {
        viewModel.viewCallHistory(callHistory)
    }
    //endregion

    override fun clear() {
        tracker?.clearSelection()
    }

    //region Companion Object
    companion object {
        const val CALLS_SELECTION_KEY = "CALLS_SELECTION_KEY"
        const val CALL_HISTORY_KEY = "callHistory"

        fun newInstance(): CallsFragment {
            return CallsFragment()
        }
    }
    //endregion
}