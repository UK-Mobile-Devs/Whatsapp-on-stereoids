package com.example.whatsapp.ui.fragments.contacts


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firestorerepository.datatypes.Contact
import com.example.whatsapp.databinding.ItemContactBinding
import com.example.whatsapp.ui.fragments.contacts.ContactsAdapter.ContactsViewHolder

class ContactsAdapter :
    ListAdapter<Contact, ContactsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ContactsViewHolder(binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {

        private val tvName = binding.tvName
        private val tvDescription = binding.tvDescription

        fun bind(contact: Contact) {
            tvName.text = "Oskar"
            tvDescription.text = "example :)"
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Contact>(){
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.uid == newItem.uid
        }

    }


}