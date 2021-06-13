package com.amit.nybooks.presentation.books

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amit.nybooks.R
import com.amit.nybooks.data.model.BookCategory
import com.amit.nybooks.data.model.BookGroup
import com.amit.nybooks.databinding.ActivityCategoryBinding

class GroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private val TAG = "GroupAcitvity"
    private lateinit var bookGroups: List<BookGroup>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarMain.title = getString(R.string.books_group)
        setSupportActionBar(binding.toolbarMain)

        val viewModel: BookCategoryViewModel = ViewModelProvider(this).get(BookCategoryViewModel::class.java)

        viewModel.bookCategoryLiveData.observe(this, Observer {
            it?.let { bookCategory ->
                with(binding.recyclerView) {
                    layoutManager =
                        LinearLayoutManager(
                            this@GroupActivity,
                            RecyclerView.VERTICAL,
                            false
                        )
                    setHasFixedSize(true)
                    bookGroups = parseGroups(bookCategory)
                    adapter = GroupAdapter(bookGroups, clickListener = {
                        appClickListener(it)
                    })
                    Log.d(TAG, "onCreate: $bookCategory")
                }
            }
        })
        viewModel.getBookCategories()
    }

    fun appClickListener(position: Int) {
        // You got the position of ArrayList
        Log.d(TAG, "appClickListener: $position")
        val intent = Intent(this@GroupActivity, BooksCategoryActivity::class.java);
        intent.putExtra("BookGroup", bookGroups[position])
        startActivity(intent)
    }

    fun parseGroups(bookCategory: List<BookCategory>) : List<BookGroup> {
        var bookGroups = mutableListOf<BookGroup>()
        bookGroups.add(0, BookGroup(mutableListOf(), "WEEKLY"))
        bookGroups.add(1, BookGroup(mutableListOf(), "MONTHLY"))
        for (item in  bookCategory) {
            if(item.updated == "WEEKLY")
                bookGroups[0].list.add(item)
            else
                bookGroups[1].list.add(item)
        }
        return bookGroups
    }
}