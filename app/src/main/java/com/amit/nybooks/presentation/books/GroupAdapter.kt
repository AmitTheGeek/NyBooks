package com.amit.nybooks.presentation.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amit.nybooks.R
import com.amit.nybooks.data.model.BookGroup

class GroupAdapter(
    private val bookGroups: List<BookGroup>, val clickListener: (Int) -> Unit
) : RecyclerView.Adapter<GroupAdapter.BookGroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookGroupViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        return BookGroupViewHolder(itemView)
    }

    override fun getItemCount() = bookGroups.size

    override fun onBindViewHolder(holder: BookGroupViewHolder, position: Int) {
        holder.bindView(bookGroups[position])
        (holder as BookGroupViewHolder).itemView.setOnClickListener {
            clickListener(position)
        }
    }

    inner class BookGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.group_name)
        private val count = itemView.findViewById<TextView>(R.id.count)

        fun bindView(bookGroup: BookGroup){
            title.text = bookGroup.updated
            count.text = bookGroup.list.size.toString()
        }
    }

}