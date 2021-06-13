package com.amit.nybooks.presentation.books

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amit.nybooks.R
import com.amit.nybooks.data.model.BookCategory

class BookCategoryAdapter(
    private val bookCategory: List<BookCategory>, val clickListener: (Int) -> Unit
) : RecyclerView.Adapter<BookCategoryAdapter.BookCategoryViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_book_category,
            parent,
            false
        )

        return BookCategoryViewHolder(itemView)

    }

    override fun getItemCount() = bookCategory.size

    override fun onBindViewHolder(holder: BookCategoryViewHolder, position: Int) {
        holder.bindView(bookCategory[position])
        (holder as BookCategoryViewHolder).itemView.setOnClickListener {
            clickListener(position)
        }
    }


    class BookCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.book_category_name)
        private val publishedDate = itemView.findViewById<TextView>(R.id.book_published_date)
        fun bindView(bookCategory: BookCategory){
            title.text = bookCategory.displayNames
            publishedDate.text = bookCategory.newestPublishedDate
        }

    }
}