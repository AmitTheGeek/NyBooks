package com.amit.nybooks.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class BookCategoryBodyResponse(
    @Json(name = "results")
    val bookCategoryResponse: List<BookCategoryResponse>
)