package com.example.whatsapp.ui.fragments.home.chats

import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentChatsBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


@AndroidEntryPoint
class ChatsFragment : BaseFragment<FragmentChatsBinding>(), ActionMode.Callback {

    //region Variables
    private val viewModel: ChatsFragmentVM by viewModels()

    private val chatsAdapter: ChatsAdapter = ChatsAdapter()

    private var tracker: SelectionTracker<Long>? = null

    var actionMode : ActionMode?= null

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

        tracker = SelectionTracker.Builder(
            CHAT_SELECTION_KEY,
            binding.rvChats,
            ChatsKeyProvider(binding.rvChats),
            ItemDetailsLookup(binding.rvChats),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()





        tracker?.addObserver(object : SelectionTracker.SelectionObserver<Long>() {
            override fun onSelectionChanged() {
                if (tracker?.hasSelection() == true) {
                    actionMode?.title = String.format("%d", tracker?.selection?.size())
                } else {
                    actionMode?.title ="0"
                }

            }

            override fun onSelectionRestored() {
                Log.e("Selected: ", "Restored")
            }

            override fun onItemStateChanged(key: Long, selected: Boolean) {
                super.onItemStateChanged(key, selected)
                Log.e("Selected: ", "New Selection $key")


                val size = tracker?.selection?.size()
                when {
                    actionMode == null -> {
                        actionMode = activity?.startActionMode(this@ChatsFragment)
                    }
                    size == 0 -> {
                        actionMode?.finish()
                    }
                    else -> {
                        actionMode?.title = tracker?.selection?.size().toString()
                    }
                }
            }

            override fun onSelectionRefresh() {
                super.onSelectionRefresh()
                Log.e("Selected: ", "Refresh")
            }
        })

        chatsAdapter.tracker = tracker

        //endregion
    }

    override fun initArgs(arguments: Bundle) {
        super.initArgs(arguments)
        tracker?.onRestoreInstanceState(arguments)
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

    //region ActionMode.Callback
    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        mode.menuInflater.inflate(R.menu.home_chats_context_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
        menu.findItem(R.id.pinChats).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu.findItem(R.id.deleteChats).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu.findItem(R.id.archiveMessages).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu.findItem(R.id.muteNotifications).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return true
    }

    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                tracker?.clearSelection()
                mode.finish()
                true
            }
            R.id.pinChats -> {
                Toast.makeText(requireContext(), "Pinned Clicked", Toast.LENGTH_SHORT).show()
                tracker?.clearSelection()
                mode.finish()
                true
            }
            R.id.deleteChats -> {
                Toast.makeText(requireContext(), "Delete Clicked", Toast.LENGTH_SHORT).show()
                mode.finish()
                true
            }
            R.id.muteNotifications -> {
                Toast.makeText(requireContext(), "Mute Clicked", Toast.LENGTH_SHORT).show()
                mode.finish()
                true
            }
            R.id.archiveMessages -> {
                Toast.makeText(requireContext(), "Archived Clicked", Toast.LENGTH_SHORT ).show()
                mode.finish()
                true
            }
            else -> {
                tracker?.clearSelection()
                mode.finish()
                false
            }
        }
    }

    override fun onDestroyActionMode(p0: ActionMode?) {
        actionMode = null
    }
    //endregion

    //region Fragment Life-Cycle
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        tracker?.onSaveInstanceState(outState)
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