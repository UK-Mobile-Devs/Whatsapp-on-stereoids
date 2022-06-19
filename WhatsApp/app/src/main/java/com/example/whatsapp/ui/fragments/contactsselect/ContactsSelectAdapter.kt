package com.example.whatsapp.ui.fragments.contactsselect

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
import com.example.firestorerepository.datatypes.Contact
import com.example.whatsapp.R
import com.example.whatsapp.databinding.ItemContactBinding
import com.example.whatsapp.databinding.ItemNewContactBinding
import com.example.whatsapp.databinding.ItemNewGroupBinding

private const val ADDED_HEADERS = 2
private const val ITEM_VIEW_TYPE_NEW_GROUP = 0
private const val ITEM_VIEW_TYPE_NEW_CONTACT = 1
private const val ITEM_VIEW_TYPE_CONTACTS = ADDED_HEADERS

class ContactsSelectAdapter :
    ListAdapter<Contact, RecyclerView.ViewHolder>(DiffCallback()) {

    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            ITEM_VIEW_TYPE_NEW_GROUP -> NewGroupViewHolder.from(parent)
            ITEM_VIEW_TYPE_NEW_CONTACT -> NewContactViewHolder.from(parent)
            ITEM_VIEW_TYPE_CONTACTS -> ContactsViewHolders.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    class ContactsViewHolders(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {

        private val tvName = binding.tvName
        private val tvDescription = binding.tvDescription
        private val lavSelected = binding.lavSelected

        fun bind(contact: Contact, isSelected : Boolean) {
            lavSelected.visibility = if(isSelected) View.VISIBLE else View.GONE
            itemView.isSelected = isSelected
            tvName.text = "Oskar $itemId"
            tvDescription.text = "example :)"
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = absoluteAdapterPosition
                override fun getSelectionKey(): Long = itemId
            }

        companion object {
            fun from(parent: ViewGroup): ContactsViewHolders {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemContactBinding.inflate(layoutInflater, parent, false)
                return ContactsViewHolders(view)
            }
        }
    }

    class NewGroupViewHolder(val binding: ItemNewGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): NewGroupViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemNewGroupBinding.inflate(layoutInflater, parent, false)
                return NewGroupViewHolder(view)
            }
        }
    }

    class NewContactViewHolder(val binding: ItemNewContactBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.ivQR.setImageResource(R.drawable.ic_qrcode)
        }
        companion object {
            fun from(parent: ViewGroup): NewContactViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemNewContactBinding.inflate(layoutInflater, parent, false)
                return NewContactViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContactsViewHolders -> {
                val newPosition = position - ADDED_HEADERS
                val item = getItem(newPosition)
                if (item != null) {
                    tracker?.let {
                        holder.bind(getItem(newPosition), it.isSelected(newPosition.toLong()))
                    }
                }

            }
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

    override fun getItemCount(): Int {
        return super.getItemCount() + ADDED_HEADERS
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_VIEW_TYPE_NEW_GROUP
            1 -> ITEM_VIEW_TYPE_NEW_CONTACT
            else -> ITEM_VIEW_TYPE_CONTACTS
        }
    }

    override fun getItemId(position: Int): Long {
        return (position - ADDED_HEADERS).toLong()
    }
}

class ContactsKeyProvider(private val recyclerView: RecyclerView) :
    ItemKeyProvider<Long>(SCOPE_MAPPED) {
    override fun getKey(position: Int): Long? {
        val theItemId = recyclerView.adapter?.getItemId(position)
        return theItemId
    }

    override fun getPosition(key: Long): Int {
        val viewHolder = recyclerView.findViewHolderForItemId(key)
        return viewHolder?.layoutPosition ?: RecyclerView.NO_POSITION
    }
}

class ContactsDetailsLookup(private val recyclerView: RecyclerView) :
    ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view: View? = recyclerView.findChildViewUnder(event.x, event.y)
        view?.let {
            val viewTrack = recyclerView.getChildViewHolder(it)

            if (viewTrack.itemId >= 0) {
                return (recyclerView.getChildViewHolder(view) as ContactsSelectAdapter.ContactsViewHolders)
                    .getItemDetails()
            }
        }
        return null
    }
}