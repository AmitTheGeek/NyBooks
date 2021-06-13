package com.amit.nybooks.data.model
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

data class BookCategory(
    val listNames: String,
    val displayNames: String,
    val listNameEncoded: String,
    val newestPublishedDate: String,
    val updated: String
) : Serializable {
    val date
        @RequiresApi(Build.VERSION_CODES.O)
        get() = newestPublishedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}