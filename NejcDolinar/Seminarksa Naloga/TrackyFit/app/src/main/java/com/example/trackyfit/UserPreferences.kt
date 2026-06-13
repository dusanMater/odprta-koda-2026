package com.example.trackyfit

import android.content.Context

class UserPreferences(context: Context) {

    private val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    // Shrani podatke
    fun saveUserData(weight: String, height: String, age: String, isMale: Boolean) {
        prefs.edit()
            .putString("weight", weight)
            .putString("height", height)
            .putString("age", age)
            .putBoolean("isMale", isMale)
            .apply()
    }

    // Naloži podatke
    fun getWeight(): String = prefs.getString("weight", "") ?: ""
    fun getHeight(): String = prefs.getString("height", "") ?: ""
    fun getAge(): String = prefs.getString("age", "") ?: ""
    fun getIsMale(): Boolean = prefs.getBoolean("isMale", true)
}