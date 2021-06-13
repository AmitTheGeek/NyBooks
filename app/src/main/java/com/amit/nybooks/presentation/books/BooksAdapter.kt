package com.amit.nybooks.presentation.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amit.nybooks.R
import com.amit.nybooks.data.model.Book

class BooksAdapter(
        private val books: List<Book>
) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)

        return BooksViewHolder(itemView)

    }

    override fun getItemCount() = books.count()

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(books[position])
    }

    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.textTitle)
        private val author = itemView.findViewById<TextView>(R.id.textAuthor)
        private val description = itemView.findViewById<TextView>(R.id.textDescription)
        private val price = itemView.findViewById<TextView>(R.id.textPrice)

        fun bindView(book: Book){
            title.text = book.title
            author.text = book.author
            description.text = book.description
            price.text = book.price.toString()
        }
    }

}