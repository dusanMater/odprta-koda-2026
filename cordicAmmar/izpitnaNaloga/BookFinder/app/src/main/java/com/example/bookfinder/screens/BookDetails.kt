package com.example.bookfinder.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bookfinder.data.Book
import java.net.URLDecoder

@Composable
fun BookDetails(bookDetails: String ?= null) {
    val decodedBookDetails = bookDetails?.let { URLDecoder.decode(it, "UTF-8") }
    val argumentsList = decodedBookDetails?.split("&")

    val bookTitle = argumentsList?.getOrNull(0)
    val bookAuthor = argumentsList?.getOrNull(1)
    val bookImageUrl = argumentsList?.getOrNull(2)
    val bookFirstPublishYear = argumentsList?.getOrNull(3)?.toIntOrNull()

    val book = Book(
        title = bookTitle ?: "Unknown Title",
        author = bookAuthor ?: "Unknown Author",
        imageUrl = bookImageUrl ?: "",
        firstPublishYear = bookFirstPublishYear
    )

    var imageLoaded by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    Log.d("BookDetails", "Decoded book details: $decodedBookDetails")
    Log.d("BookDetails", "Parsed book: $book")

    Column {
        Log.d("BookDetails", "Loading image from URL: ${book.imageUrl}")
        AsyncImage(
            model = book.imageUrl,
            contentDescription = "Book Cover",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(200.dp),
            onSuccess = {
                imageLoaded = true
                loading = false
            },
            onError = {
                error = "Failed to load image"
                loading = false

                Log.e("BookDetails", "Error loading image: ${it.result.throwable.message}")
            }
        )

        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
        } else if (error != null) {
            Text(
                text = error ?: "Unknown error",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(30.dp)
            )
        }

        if (imageLoaded) {
            Text(
                text = "Title: ${book.title}",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Author: ${book.author}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "First Published: ${book.firstPublishYear ?: "Unknown"}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }


    }

}