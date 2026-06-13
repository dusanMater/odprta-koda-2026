package com.example.projektnanaloga.network

import com.example.projektnanaloga.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("3/search/movie")
    suspend fun getFilmi(
        @Query("api_key") apiKljuc: String,
        @Query("query") iskanje: String,
        @Query("language") jezik: String
    ): MovieResponse
}
