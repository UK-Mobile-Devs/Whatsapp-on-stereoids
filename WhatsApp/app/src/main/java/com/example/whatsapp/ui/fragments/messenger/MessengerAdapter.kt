package com.example.whatsapp.ui.fragments.messenger


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.firestorerepository.models.MessageDTO
import com.example.firestorerepository.models.MessageType
import com.example.whatsapp.databinding.ItemIncomingMessageBinding
import com.example.whatsapp.databinding.ItemMessageBinding

class MessengerAdapter :
    ListAdapter<MessageDTO, ViewHolder>(DiffCallback()) {

    init {
        setHasStableIds(true)
    }

    //region ListAdapter Overrides
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            MessageType.PERSONAL.type -> {
                val view =
                    ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PersonalMessageViewHolder(view)
            }
            MessageType.INCOMING.type -> {
                val view = ItemIncomingMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                IncomingMessageViewHolder(view)
            }
            else -> throw IllegalArgumentException("Incorrect message type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).messageType.type
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder.itemViewType) {
            MessageType.PERSONAL.type -> (holder as PersonalMessageViewHolder).bind(getItem(position))
            MessageType.INCOMING.type -> (holder as IncomingMessageViewHolder).bind(getItem(position))
        }
    }

    override fun getItemId(position: Int): Long = position.toLong()

    //endregion

    //region ItemCallback
    class DiffCallback : DiffUtil.ItemCallback<MessageDTO>() {

        override fun areItemsTheSame(oldItem: MessageDTO, newItem: MessageDTO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MessageDTO, newItem: MessageDTO): Boolean {
            return oldItem.id == newItem.id
        }


    }
    //endregion

}