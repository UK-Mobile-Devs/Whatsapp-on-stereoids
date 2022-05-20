package com.example.whatsapp.ui.fragments.home.chathistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firestorerepository.datatypes.Call
import com.example.whatsapp.R
import com.example.whatsapp.databinding.ItemChatHistoryBinding

class ChatHistoryAdapter() : ListAdapter<Call, ChatHistoryAdapter.ChatHistoryViewHolder>(ChatHistoryViewHolder.DiffCallback()) {

    //region ListAdapter Overrides
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHistoryViewHolder {
        val view = ItemChatHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatHistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    //endregion

    //region CallsViewHolder
    class ChatHistoryViewHolder(binding: ItemChatHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

        //region Variables
        private val tvCallState = binding.idCallState
        private val tvTime = binding.idCallTime
        private val ivCallDirection = binding.ivCallDirection


        //endregion

        fun bind(call: Call) {

            tvCallState.text = "Inbound"
            tvTime.text = "12:00"

            ivCallDirection.setImageResource(
                if(call.isVideoCall == true)
                    R.drawable.ic_video else R.drawable.ic_call
            )

//            ivCallDirection.setColorFilter(
//                if (call.isInBound == true)
//                    R.drawable.ic_call_made
//                else
//                    R.drawable.ic_call_received,
//                if (call.isInBound == true)
//                    R.color.call_inbound
//                else
//                    R.color.call_outbound
//            )

        }
        //endregion

        //region ItemCallback
        class DiffCallback : DiffUtil.ItemCallback<Call>() {

            override fun areItemsTheSame(oldItem: Call, newItem: Call): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Call, newItem: Call): Boolean {
                return oldItem.uid == newItem.uid
            }
        }
        //endregion

    }
}
