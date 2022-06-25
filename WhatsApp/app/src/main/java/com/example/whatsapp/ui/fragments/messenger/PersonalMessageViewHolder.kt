package com.example.whatsapp.ui.fragments.messenger

import androidx.recyclerview.widget.RecyclerView
import com.example.firestorerepository.models.MessageDTO
import com.example.whatsapp.databinding.ItemMessageBinding

class PersonalMessageViewHolder(val binding: ItemMessageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(message: MessageDTO) {
        binding.tvMessage.text = "test message :) "
        binding.tvTimeReceived.text = "15:30"
    }

}