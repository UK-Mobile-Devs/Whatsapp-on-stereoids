package com.example.whatsapp.ui.fragments.home.chats

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firestorerepository.datatypes.Conversation
import com.example.whatsapp.databinding.ItemChatBinding


class ChatsKeyProvider(private val chatsAdapter: ChatsAdapter) :
    ItemKeyProvider<Conversation>(SCOPE_CACHED) {

    override fun getKey(position: Int): Conversation {
        return chatsAdapter.getItem(position)
    }

    override fun getPosition(conversation: Conversation): Int {
        return chatsAdapter.getItemPosition(conversation)
    }
}


data class ConversationDetails(
    private val position: Int = 0,
    private val conversation: Conversation?
) : ItemDetailsLookup.ItemDetails<Conversation>() {

    override fun getPosition(): Int = position

    override fun getSelectionKey(): Conversation? = conversation
}

class ItemDetailsLookup(private val recyclerView: RecyclerView) :
    ItemDetailsLookup<Conversation>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Conversation>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as ChatsAdapter.ChatsViewHolder).getItemDetails()
        }
        return null
    }
}


class ChatsAdapter : ListAdapter<Conversation, ChatsAdapter.ChatsViewHolder>(DiffCallback()) {

    var tracker: SelectionTracker<Conversation>? = null

    init {
        setHasStableIds(true)
    }

    //region ListAdapter Overrides
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val view = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        tracker?.let {
            holder.bind(getItem(position), it.isSelected(getItem(position)))
        }
    }

    override fun getItemCount(): Int = currentList.size


    public override fun getItem(position: Int): Conversation = currentList[position]
    fun getItemPosition(conversation : Conversation): Int = currentList.indexOf(conversation)

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int): Long = position.toLong()

    //endregion

    //region ChatsViewHolder
    class ChatsViewHolder(binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {

        //region Variables
        private val tvTitle = binding.tvTitle
        private val tvBody = binding.tvBody
        private val tvTime = binding.tvTime
        private val ivIcon = binding.ivIcon
        private val lavSelected = binding.lavSelected
        //endregion
        private var conversation : Conversation?= null

        fun bind(conversation: Conversation, isSelected : Boolean) {

            this.conversation = conversation
            lavSelected.visibility = if(isSelected) View.VISIBLE else View.GONE

            if(!conversation.isGroup) {
                // Todo: Add actual data here from the conversation, but the database structure is currently TBT
                tvTitle.text = "Bill Gates"
                tvBody.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                tvTime.text = "08/04/2022"
            }
            else {
                tvTitle.text = "The boys"
                tvBody.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                tvTime.text = "15/05/2022"
            }
        }


        fun getItemDetails(): ConversationDetails = ConversationDetails(
            position = bindingAdapterPosition,
            conversation = conversation
        )
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