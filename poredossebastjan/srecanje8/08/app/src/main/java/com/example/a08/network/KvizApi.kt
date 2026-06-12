package com.example.a08.network

import com.example.a08.model.KvizResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KvizApi {

    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") amount: Int = 10,
        @Query("category") category: Int = 12,
        @Query("difficulty") difficulty: String = "easy",
        @Query("type") type: String = "multiple"
    ): KvizResponse
}