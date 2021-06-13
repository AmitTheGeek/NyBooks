package com.amit.nybooks.presentation.books

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amit.nybooks.R
import com.amit.nybooks.data.model.BookCategory
import com.amit.nybooks.data.model.BookGroup
import com.amit.nybooks.databinding.ActivityCategoryBinding
import java.util.*

class BooksCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private val TAG = "BookCategoryActivity"
    private lateinit var booklist: List<BookCategory>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarMain.title = getString(R.string.books_category)
        setSupportActionBar(binding.toolbarMain)

        booklist = (intent.getSerializableExtra("BookGroup") as BookGroup).list
        val comparator = compareBy<BookCategory> { it.date }
        Collections.sort(booklist, comparator)
        binding.recyclerView
        val layoutManager =
            LinearLayoutManager(
                this@BooksCategoryActivity,
                RecyclerView.VERTICAL,
                false
            )
        binding.recyclerView.setHasFixedSize(true)
        val adapter = BookCategoryAdapter(booklist, clickListener = {
            appClickListener(it)
        })
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    fun appClickListener(position: Int) {
        // You got the position of ArrayList
        Log.d(TAG, "appClickListener: $position")
        val intent = Intent(this@BooksCategoryActivity, BooksActivity::class.java);
        intent.putExtra("BookGroup", booklist[position].listNameEncoded)
        startActivity(intent)
    }

}

