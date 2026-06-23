package com.example.triviaapp.network

import com.example.triviaapp.model.QuestionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaApi {
    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String
    ): QuestionsResponse
}

// API Endpoint: https://opentdb.com/api.php?amount=10&category=18&difficulty=medium&type=multiple