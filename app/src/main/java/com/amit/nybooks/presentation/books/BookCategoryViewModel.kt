package com.amit.nybooks.presentation.books

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amit.nybooks.data.ApiService
import com.amit.nybooks.data.model.BookCategory
import com.amit.nybooks.data.response.BookCategoryBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookCategoryViewModel : ViewModel(){
    companion object {
        private const val TAG = "BookCategoryViewModel"
    }
    val bookCategoryLiveData: MutableLiveData<List<BookCategory>> = MutableLiveData()

    fun getBookCategories(){
        ApiService.service.getBookCategories().enqueue(object : Callback<BookCategoryBodyResponse> {
            override fun onResponse(
                call: Call<BookCategoryBodyResponse>,
                response: Response<BookCategoryBodyResponse>
            ) {
                if (response.isSuccessful) {
                    val bookcategories: MutableList<BookCategory> = mutableListOf()
                    response.body()?.let { bookCategoryBodyResponse ->
                        for (result in bookCategoryBodyResponse.bookCategoryResponse) {
                            Log.d(TAG, "bookcategories onResponse: $result")
                            val bookCategory = BookCategory (
                                listNames = result.listName,
                                displayNames = result.displayName,
                                listNameEncoded = result.listNameEncoded,
                                newestPublishedDate = result.newestPublishedDate,
                                updated = result.updated
                                    )
                            bookcategories.add(bookCategory)
                        }
                    }

                    bookCategoryLiveData.value = bookcategories
                }
            }

            override fun onFailure(call: Call<BookCategoryBodyResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }
}