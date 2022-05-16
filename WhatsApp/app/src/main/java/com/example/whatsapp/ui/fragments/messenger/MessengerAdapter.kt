package com.example.whatsapp.ui.fragments.messenger


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firestorerepository.datatypes.Conversation
import com.example.firestorerepository.datatypes.Message
import com.example.whatsapp.databinding.ItemMessageBinding

class MessengerAdapter :
    ListAdapter<Message, MessengerAdapter.MessengerViewHolder>(DiffCallback()) {

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
    class MessengerViewHolder(val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.tvMessage.text = "test message :) "
            binding.tvTimeReceived.text = "15:30"
        }

    }
    //endregion

    //region ItemCallback
    class DiffCallback : DiffUtil.ItemCallback<Message>() {

        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id == newItem.id
        }


    }
    //endregion

}