package com.amit.nybooks.data

import com.amit.nybooks.data.response.BookBodyResponse
import com.amit.nybooks.data.response.BookCategoryBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "VWW4WCl2PsYEGIhZJnD8a1txhStfCJGZ",
        @Query("list") list: String
    ): Call<BookBodyResponse>

    @GET("lists/names.json")
    fun getBookCategories(
        @Query("api-key") apiKey: String = "VWW4WCl2PsYEGIhZJnD8a1txhStfCJGZ"
    ): Call<BookCategoryBodyResponse>
}