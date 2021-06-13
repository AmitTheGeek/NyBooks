package com.amit.nybooks.presentation.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amit.nybooks.R
import com.amit.nybooks.data.model.BookCategory
import com.amit.nybooks.databinding.ActivityBooksBinding

class BooksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBooksBinding
    private lateinit var list : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarMain.title = getString(R.string.books_title)
        setSupportActionBar(binding.toolbarMain)
        list = intent.getStringExtra("BookGroup" ) as String
        val viewModel: BooksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this, Observer {
            it?.let { books ->
                with(binding.recyclerBooks) {
                    layoutManager =
                        LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books)
                }
            }
        })
        viewModel.getBooks(list)
    }


}