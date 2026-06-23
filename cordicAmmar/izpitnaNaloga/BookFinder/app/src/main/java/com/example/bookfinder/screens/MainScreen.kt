package com.example.bookfinder.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookfinder.data.Book
import com.example.bookfinder.data.BooksSearchResponse
import com.example.bookfinder.data.SharedPrefsManager
import com.example.bookfinder.network.BooksSearchApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder
import kotlin.getValue

@Composable
fun MainScreen(navController: NavController) {

    var books by remember { mutableStateOf(BooksSearchResponse(emptyList(), 0, 0)) }
    var searchText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val sharedPrefsManager = SharedPrefsManager(context = context)
    val favouriteBooks = remember { mutableStateListOf<Book>() }
    val loadedFavourites = sharedPrefsManager.loadFavorites()

    var searchErrorMessage by remember { mutableStateOf<String?>(null) }

    loadedFavourites.forEach { book ->
        if (!favouriteBooks.any { it.title == book.title }) {
            favouriteBooks.add(book)
        }
    }


    LaunchedEffect(isLoading) {
        if (isLoading) {
            try {
                val response = RetrofitInstance.api.searchBooks(searchText)
                books = response
            } catch (e: Exception) {
                searchErrorMessage = "Error fetching books: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding(),
    ) {
        Text(
            text = "Book Finder",
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        ) {


            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                label = { Text("Search for books") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(2f)
            )

            Button(
                onClick = {
                    isLoading = true
                    searchErrorMessage = null
                },
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text("Search")
            }

        }

        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }

            if (searchErrorMessage != null) {
                Text(
                    text = searchErrorMessage ?: "",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            if (!books.docs.isEmpty() && !isLoading) {
                Text(
                    text = "Search results:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                books.docs.forEach { book ->

                    Card (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "${book.title} \n${book.author_name?.joinToString(", ") ?: "Unknown author"}",
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable(
                                        onClick = {
                                            val bookDetails =
                                                "${book.title}&${book.author_name?.joinToString(", ") ?: "Unknown author"}&${book.cover_i?.let { "https://covers.openlibrary.org/b/id/$it-M.jpg" } ?: ""}&${book.first_publish_year ?: "Unknown year"}"
                                            val encodedBookDetails = URLEncoder.encode(bookDetails, "UTF-8")
                                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                                "bookDetails",
                                                bookDetails
                                            )
                                            navController.navigate("details/${encodedBookDetails}")
                                        }
                                    ),
                                fontSize = 16.sp
                            )

                            IconButton(onClick = {
                                if (favouriteBooks.any { it.title == book.title }) {
                                    favouriteBooks.removeAll { it.title == book.title }
                                    sharedPrefsManager.removeFavorite(book.toBook())
                                } else {
                                    val newBook = Book(
                                        title = book.title,
                                        author = book.author_name?.joinToString(", ") ?: "Unknown author",
                                        imageUrl = book.cover_i?.let { "https://covers.openlibrary.org/b/id/$it-M.jpg" } ?: "",
                                        firstPublishYear = book.first_publish_year
                                    )
                                    favouriteBooks.add(newBook)
                                    sharedPrefsManager.saveFavorite(newBook)
                                }
                            }) {
                                if (favouriteBooks.any { it.title == book.title }) {
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = "Remove from favourites",
                                        tint = Color.Red
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = "Add to favourites"
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

object RetrofitInstance {
    val api: BooksSearchApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://openlibrary.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksSearchApi::class.java)
    }
}