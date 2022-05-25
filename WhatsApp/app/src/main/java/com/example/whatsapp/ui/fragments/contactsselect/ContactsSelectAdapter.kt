package com.example.whatsapp.ui.fragments.contactsselect

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firestorerepository.datatypes.Contact
import com.example.whatsapp.R
import com.example.whatsapp.databinding.ItemContactBinding

private const val LOGTAG = "ContactsSelectAdapterTest"
private const val ADDEDED_HEADERS = 2
private const val ITEM_VIEW_TYPE_NEW_GROUP = 0
private const val ITEM_VIEW_TYPE_NEW_CONTACT = 1
private const val ITEM_VIEW_TYPE_CONTACTS = ADDEDED_HEADERS

class ContactsSelectAdapter :
    ListAdapter<Contact, RecyclerView.ViewHolder>(DiffCallback()) {

    var tracker: SelectionTracker<Long>? = null

//    init {
//        setHasStableIds(true)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

//        Log.i(LOGTAG, "onCreateViewHolder $viewType")
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
            tvName.text = "Oskar"
            tvDescription.text = "example :)"
            Log.i(LOGTAG, "ITEM ID: $itemId ITEM NUM: $contact")
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

    class NewGroupViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): NewGroupViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_new_group, parent, false)
                return NewGroupViewHolder(view)
            }
        }
    }

    class NewContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Set QRcode
        val qrIv: ImageView = view.findViewById(R.id.ivQR)
        init {
            qrIv.setImageResource(R.drawable.ic_qrcode)
        }
        companion object {
            fun from(parent: ViewGroup): NewContactViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_new_contact, parent, false)
                return NewContactViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.i(LOGTAG, "Position $position - onBindVH")
        when (holder) {
            is ContactsViewHolders -> {
                val newPosition = position - ADDEDED_HEADERS
                val item = getItem(newPosition)
                if (item != null) {
                    tracker?.let {
                        holder.bind(getItem(newPosition), it.isSelected(newPosition.toLong()))
                    }
//                    holder.bind(item)
                    Log.i(LOGTAG, "$item Position: $newPosition")
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
        return super.getItemCount() + ADDEDED_HEADERS
    }

    override fun getItemViewType(position: Int): Int {
//        Log.i(LOGTAG, "getItemView")
        return if (position == 0) {
            ITEM_VIEW_TYPE_NEW_GROUP
        } else if (position == 1) {
            ITEM_VIEW_TYPE_NEW_CONTACT
        } else {
            ITEM_VIEW_TYPE_CONTACTS
        }
    }
}

class ContactsKeyProvider(private val recyclerView: RecyclerView) :
    ItemKeyProvider<Long>(SCOPE_MAPPED) {

    override fun getKey(position: Int): Long? {
        val theItemId = recyclerView.adapter?.getItemId(position - ADDEDED_HEADERS)
        Log.i(LOGTAG, "GetKey: $theItemId")
        return theItemId
    }

    override fun getPosition(key: Long): Int {
        val viewHolder = recyclerView.findViewHolderForItemId(key)
        Log.i(LOGTAG, "getPosition: $viewHolder")
        return viewHolder?.layoutPosition ?: RecyclerView.NO_POSITION
    }
}

class ContactsDetailsLookup(private val recyclerView: RecyclerView) :
    ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetailsLookup.ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as ContactsSelectAdapter.ContactsViewHolders)
                .getItemDetails()
        }
        return null
    }
}