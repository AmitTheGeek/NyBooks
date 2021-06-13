package com.amit.nybooks.data.model

import java.io.Serializable


data class BookGroup(
    var list: MutableList<BookCategory>,
    var updated: String
) : Serializable