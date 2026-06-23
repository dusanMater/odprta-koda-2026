package com.example.bookfinder.data

data class Book(
    val title: String,
    val author: String,
    val imageUrl: String,
    val coverEditionKey: String? = null,
    val firstPublishYear: Int? = null
)
