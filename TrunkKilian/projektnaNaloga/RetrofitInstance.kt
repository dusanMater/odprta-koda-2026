package com.example.projektnanaloga.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}

/* curl "https://api.themoviedb.org/3/search/movie?api_key=9a8da407086d542077fdd63a0cb2e08b&query=Batman&language=sl-SI" */
