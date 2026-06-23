package com.example.bookfinder.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookfinder.data.Book
import com.example.bookfinder.data.SharedPrefsManager
import java.net.URLEncoder

@Composable
fun FavouritesScreen(navController: NavController) {
    val sharedPrefsManager = SharedPrefsManager(context = LocalContext.current)
    val favouriteBooks = remember {
        mutableStateListOf<Book>().apply {
            addAll(sharedPrefsManager.loadFavorites())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding(),
    ) {
        Text(
            text = "Favourites",
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        if (favouriteBooks.isEmpty()) {
            Text(
                text = "No favourite books found.",
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyColumn {
                items(favouriteBooks) { book ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Spacer(modifier = Modifier.width(16.dp))
                            Column (
                                modifier = Modifier.weight(2f).clickable(
                                    onClick = {
                                        val bookDetails =
                                            "${book.title}&${book.author}&${book.imageUrl }&${book.firstPublishYear ?: "Unknown year"}"
                                        val encodedBookDetails = URLEncoder.encode(bookDetails, "UTF-8")
                                        navController.currentBackStackEntry?.savedStateHandle?.set(
                                            "bookDetails",
                                            bookDetails
                                        )
                                        navController.navigate("details/${encodedBookDetails}")
                                    }
                                )
                            ) {
                                Text(text = book.title, style = MaterialTheme.typography.titleMedium)
                                Text(text = book.author, style = MaterialTheme.typography.bodyMedium)
                            }
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(40.dp).weight(1f).clickable(
                                    onClick = {
                                        sharedPrefsManager.removeFavorite(book)
                                        favouriteBooks.remove(book)
                                    }
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}