package com.example.bookfinder.network

import com.example.bookfinder.data.BooksSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksSearchApi {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): BooksSearchResponse
}