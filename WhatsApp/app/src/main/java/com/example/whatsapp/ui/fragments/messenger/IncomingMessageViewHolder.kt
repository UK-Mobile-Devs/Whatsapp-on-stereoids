package com.example.whatsapp.ui.fragments.messenger

import androidx.recyclerview.widget.RecyclerView
import com.example.firestorerepository.models.MessageDTO
import com.example.whatsapp.databinding.ItemIncomingMessageBinding

class IncomingMessageViewHolder(val binding: ItemIncomingMessageBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(message: MessageDTO) {
        binding.tvMessage.text = "incoming message :) "
        binding.tvTimeReceived.text = "15:30"
    }

}