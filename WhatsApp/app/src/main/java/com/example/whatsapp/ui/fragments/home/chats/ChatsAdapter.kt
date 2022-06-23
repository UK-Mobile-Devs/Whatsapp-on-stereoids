package com.example.whatsapp.ui.fragments.home.chats

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
import com.example.firestorerepository.datatypes.Conversation
import com.example.whatsapp.databinding.ItemChatBinding


class ChatsKeyProvider(private val recyclerView: RecyclerView) :
    ItemKeyProvider<Long>(SCOPE_MAPPED) {

    override fun getKey(position: Int): Long? {
        return recyclerView.adapter?.getItemId(position)
    }

    override fun getPosition(key: Long): Int {
        val viewHolder = recyclerView.findViewHolderForItemId(key)
        return viewHolder?.layoutPosition ?: RecyclerView.NO_POSITION
    }
}

class ChatDetailsLookup(private val recyclerView: RecyclerView) :
    ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as ChatsAdapter.ChatsViewHolder)
                .getItemDetails()
        }
        return null
    }
}


class ChatsAdapter(val callback: ChatsCallback) :
    ListAdapter<Conversation, ChatsAdapter.ChatsViewHolder>(DiffCallback()) {

    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    //region ListAdapter Overrides
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val view = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        tracker?.let {
            holder.bind(getItem(position), it.isSelected(position.toLong()))
        }
    }

    override fun getItemId(position: Int): Long = position.toLong()

    //endregion

    //region ChatsViewHolder
    class ChatsViewHolder(binding: ItemChatBinding, private val callback: ChatsCallback) :
        RecyclerView.ViewHolder(binding.root) {

        //region Variables
        private val layout = binding.layoutMessage
        private val tvTitle = binding.tvTitle
        private val tvBody = binding.tvBody
        private val tvTime = binding.tvTime

        private val lavSelected = binding.lavSelected
        //endregion

        fun bind(conversation: Conversation, isSelected: Boolean) {
            layout.setOnClickListener {
                callback.onChatSelected(conversation.uid)
            }
            lavSelected.visibility = if (isSelected) View.VISIBLE else View.GONE

            itemView.isSelected = isSelected

            if (!conversation.isGroup) {
                // Todo: Add actual data here from the conversation, but the database structure is currently TBT
                tvTitle.text = "Bill Gates"
                tvBody.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                tvTime.text = "08/04/2022"
            } else {
                tvTitle.text = "The boys"
                tvBody.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                tvTime.text = "15/05/2022"
            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = absoluteAdapterPosition
                override fun getSelectionKey(): Long = itemId
            }
    }
    //endregion

    interface ChatsCallback {
        fun onChatSelected(uid: String)
    }

    //region ItemCallback
    class DiffCallback : DiffUtil.ItemCallback<Conversation>() {

        override fun areItemsTheSame(oldItem: Conversation, newItem: Conversation): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Conversation, newItem: Conversation): Boolean {
            return oldItem.uid == newItem.uid
        }
    }
    //endregion
}