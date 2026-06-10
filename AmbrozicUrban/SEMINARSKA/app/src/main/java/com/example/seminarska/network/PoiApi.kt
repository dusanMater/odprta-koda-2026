package com.example.seminarska.network

import com.example.seminarska.model.PoiRezultat
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PoiApi {
    @GET("search")
    suspend fun isciPoi(
        @Header("User-Agent") userAgent: String,
        @Query("q") poizvedba: String,
        @Query("format") format: String = "json",
        @Query("limit") limit: Int = 10
    ): List<PoiRezultat>
}
