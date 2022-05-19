package com.example.whatsapp.ui.fragments.home.calls

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firestorerepository.datatypes.CallHistory
import com.example.whatsapp.R
import com.example.whatsapp.databinding.ItemCallBinding
import com.example.whatsapp.utils.setLeftDrawableIconAndColour


class CallsKeyProvider(private val recyclerView: RecyclerView) :
    ItemKeyProvider<Long>(SCOPE_MAPPED) {

    override fun getKey(position: Int): Long? {
        return recyclerView.adapter?.getItemId(position)
    }

    override fun getPosition(key: Long): Int {
        val viewHolder = recyclerView.findViewHolderForItemId(key)
        return viewHolder?.layoutPosition ?: RecyclerView.NO_POSITION
    }
}

class CallsDetailsLookup(private val recyclerView: RecyclerView) :
    ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as CallsAdapter.CallsViewHolder).getItemDetails()
        }
        return null
    }
}


class CallsAdapter : ListAdapter<CallHistory, CallsAdapter.CallsViewHolder>(DiffCallback()) {

    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    //region ListAdapter Overrides
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallsViewHolder {
        val view = ItemCallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CallsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CallsViewHolder, position: Int) {
        tracker?.let {
            holder.bind(getItem(position), it.isSelected(position.toLong()))
        }
    }

    override fun getItemId(position: Int): Long = position.toLong()

    //endregion

    //region CallsViewHolder
    class CallsViewHolder(binding: ItemCallBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        //region Variables
        private val tvTitle = binding.tvCallerTitle
        private val tvBody = binding.tvBody
        private val ivIcon = binding.ivIcon
        private val ivRecentCallType = binding.ivRecentCallType
        private val lavSelected = binding.lavSelected
        private val clParent = binding.clParent
        //endregion

        fun bind(callHistory: CallHistory, isSelected: Boolean) {

            clParent.isSelected = isSelected
            lavSelected.visibility = if (isSelected) View.VISIBLE else View.GONE

            clParent.setOnClickListener(this)
            ivRecentCallType.setOnClickListener(this)


            ivRecentCallType.setImageResource(
                if (callHistory.calls.last().isVideoCall == true)
                    R.drawable.ic_video else R.drawable.ic_call
            )

            tvBody.setLeftDrawableIconAndColour(
                if (callHistory.calls.last().isInBound == true)
                    R.drawable.ic_call_made
                else
                    R.drawable.ic_call_received,
                if (callHistory.calls.last().isInBound == true)
                    R.color.call_inbound
                else
                    R.color.call_outbound
            )

        }

        //region View.OnClickListener
        override fun onClick(view: View) {
            Log.e("Click, ", "click")
            when(view) {
                ivRecentCallType -> {

                }
                else -> {

                }
            }
        }

        //endregion



        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = absoluteAdapterPosition
                override fun getSelectionKey(): Long = itemId
            }
    }
    //endregion

    //region ItemCallback
    class DiffCallback : DiffUtil.ItemCallback<CallHistory>() {

        override fun areItemsTheSame(oldItem: CallHistory, newItem: CallHistory): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CallHistory, newItem: CallHistory): Boolean {
            return oldItem.uid == newItem.uid
        }
    }
    //endregion
}
