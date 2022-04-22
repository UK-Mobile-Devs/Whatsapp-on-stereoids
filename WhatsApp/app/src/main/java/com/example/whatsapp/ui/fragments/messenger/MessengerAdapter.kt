package com.example.whatsapp.ui.fragments.messenger


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firestorerepository.datatypes.Conversation
import com.example.whatsapp.databinding.ItemMessageBinding

class MessengerAdapter :
    ListAdapter<Conversation, MessengerAdapter.MessengerViewHolder>(DiffCallback()) {

    //region ListAdapter Overrides
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessengerViewHolder {
        val view = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessengerViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessengerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    //endregion

    //region ViewHolder
    class MessengerViewHolder(binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(conversation: Conversation) {

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