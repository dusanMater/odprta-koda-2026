package com.example.projektnanaloga

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun shraniIgre(context: Context, igre: List<Igra>) {
    val skupnePreference = context.getSharedPreferences("GameDealerPrefs", Context.MODE_PRIVATE)
    val urednik = skupnePreference.edit()
    val gson = Gson()
    val json = gson.toJson(igre)
    urednik.putString("MOJE_IGRE", json)
    urednik.apply()
}

fun naloziIgre(context: Context): List<Igra> {
    val skupnePreference = context.getSharedPreferences("GameDealerPrefs", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = skupnePreference.getString("MOJE_IGRE", null)
    return if (json != null) {
        val tip = object : TypeToken<List<Igra>>() {}.type
        gson.fromJson(json, tip)
    } else {
        emptyList()
    }
}