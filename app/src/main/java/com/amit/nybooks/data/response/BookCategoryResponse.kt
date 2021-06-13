package com.amit.nybooks.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookCategoryResponse (
    @Json(name = "list_name")
    val listName: String,
    @Json(name = "display_name")
    val displayName: String,
    @Json(name = "list_name_encoded")
    val listNameEncoded: String,
    @Json(name = "newest_published_date")
    val newestPublishedDate: String,
    @Json(name = "updated")
    val updated: String
)