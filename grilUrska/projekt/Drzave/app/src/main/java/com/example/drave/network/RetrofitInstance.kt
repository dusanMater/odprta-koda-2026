package com.example.drave.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: CountryApi by lazy {

        Retrofit.Builder()
            .baseUrl(
                "https://api.restcountries.com/"
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(CountryApi::class.java)

    }

}