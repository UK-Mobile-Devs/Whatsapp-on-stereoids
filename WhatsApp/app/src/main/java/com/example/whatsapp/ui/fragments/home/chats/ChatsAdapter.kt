package com.example.whatsapp.ui.fragments.home.chats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firestorerepository.datatypes.Conversation
import com.example.whatsapp.databinding.ItemChatBinding

class ChatsAdapter : ListAdapter<Conversation, ChatsAdapter.ChatsViewHolder>(DiffCallback()) {

    //region ListAdapter Overrides
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val view = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    //endregion

    //region ChatsViewHolder
    class ChatsViewHolder(binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {

        //region Variables
        private val tvTitle = binding.tvTitle
        private val tvBody = binding.tvBody
        private val tvTime = binding.tvTime
        private val ivIcon = binding.ivIcon
        //endregion

        fun bind(conversation: Conversation) {
            // Todo: Add actual data here from the conversation, but the database structure is currently TBT
            conversation.uid
            tvTitle.text = "Bill Gates"
            tvBody.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
            tvTime.text = "08/04/2022"
        }
    }
    //endregion

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