package com.example.bookfinder.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.content.edit

class SharedPrefsManager(context: Context) {

    private val prefs =
        context.getSharedPreferences("book_prefs", Context.MODE_PRIVATE)

    private val gson = Gson()

    companion object {
        private const val FAVORITES_KEY = "favorites"
    }

    fun saveFavorites(favorites: List<Book>) {

        val json = gson.toJson(favorites)

        prefs.edit {
            putString(FAVORITES_KEY, json)
        }
    }

    fun loadFavorites(): MutableList<Book> {

        val json = prefs.getString(FAVORITES_KEY, null)

        if (json == null) {
            return mutableListOf()
        }

        val type = object : TypeToken<MutableList<Book>>() {}.type

        return gson.fromJson(json, type)
    }

    fun removeFavorite(book: Book) {
        val favorites = loadFavorites()
        favorites.removeAll { it.title == book.title }
        saveFavorites(favorites)
    }

    fun saveFavorite(book: Book) {
        val favorites = loadFavorites()
        if (!favorites.any { it.title == book.title }) {
            favorites.add(book)
            saveFavorites(favorites)
        }
    }
}