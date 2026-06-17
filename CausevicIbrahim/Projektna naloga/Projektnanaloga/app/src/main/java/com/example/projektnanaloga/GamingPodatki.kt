package com.example.projektnanaloga

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class CheapSharkIgra(
    val gameID: String,
    val external: String,
    val cheapest: String,
    val thumb: String,
    val cheapestDealID: String? = null
)

data class GameLookup(
    val info: GameLookupInfo?,
    val deals: List<Deal>?
)

data class GameLookupInfo(
    val title: String?,
    val steamAppID: String?,
    val thumb: String?
)

data class Deal(
    val dealID: String?,
    val storeID: String?,
    val price: String?,
    val retailPrice: String?
)

data class Store(
    val storeID: String,
    val storeName: String
)

data class Igra(
    val id: String,
    val naslov: String,
    val cena: String,
    val slikaUrl: String,
    val store: String = "",
    val dealUrl: String = ""
)

interface CheapSharkApi {
    @GET("api/1.0/games")
    suspend fun poisciIgre(
        @Query("title") naslov: String,
        @Query("limit") omejitev: Int = 10
    ): List<CheapSharkIgra>

    @GET("api/1.0/games")
    suspend fun pridobiIgra(@Query("id") idIgre: String): GameLookup

    @GET("api/1.0/stores")
    suspend fun dohvatiTrgovine(): List<Store>
}

object RetrofitClient {
    val api: CheapSharkApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.cheapshark.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CheapSharkApi::class.java)
    }
}