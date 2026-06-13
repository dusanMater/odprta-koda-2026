package com.example.drave.network

import com.example.drave.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CountryApi {

    @GET("countries/v5")

    suspend fun getCountry(

        @Query("q")
        country: String,

        @Header("Authorization")
        token: String

    ): ApiResponse
}