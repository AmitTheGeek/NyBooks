package com.amit.nybooks.presentation.books


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amit.nybooks.data.ApiService
import com.amit.nybooks.data.model.Book
import com.amit.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel(){

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks(list: String){
        ApiService.service.getBooks(list=list).enqueue(object: Callback<BookBodyResponse>{
            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                if (response.isSuccessful){
                    val books: MutableList<Book> = mutableListOf()
                    response.body()?.let{bookBodyResponse ->
                        for(result in bookBodyResponse.bookResults){
                            val book = Book(
                                title = result.bookDetailsResponse[0].title,
                                author = result.bookDetailsResponse[0].author,
                                description = result.bookDetailsResponse[0].description,
                                price = result.bookDetailsResponse[0].price
                            )
                            books.add(book)
                        }
                    }

                    booksLiveData.value = books
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}