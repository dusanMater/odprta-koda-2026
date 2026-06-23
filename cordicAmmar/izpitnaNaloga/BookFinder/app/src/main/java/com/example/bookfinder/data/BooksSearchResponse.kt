package com.example.bookfinder.data

data class BooksSearchResponse(
    val docs: List<BookDoc>,
    val numFound: Int,
    val start: Int
)

data class BookDoc(
    val title: String,
    val author_name: List<String>?,
    val cover_i: Int?,
    val cover_edition_key: String?,
    val first_publish_year: Int?
) {
    fun toBook(): Book {
        val author = author_name?.joinToString(", ") ?: "Unknown Author"
        val imageUrl = cover_i?.let { "https://covers.openlibrary.org/b/id/$it-L.jpg" } ?: ""
        return Book(title, author, imageUrl)
    }
}


