package com.example.seminarska

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Shramba {
    private const val PREFS = "POI_App"
    private const val KLJUC = "priljubljeni_seznam"
    private val gson = Gson()

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun naloziPriljubljene(context: Context): MutableList<String> {
        val json = prefs(context).getString(KLJUC, null) ?: return mutableListOf()
        val tip = object : TypeToken<MutableList<String>>() {}.type
        return gson.fromJson(json, tip) ?: mutableListOf()
    }

    private fun shrani(context: Context, seznam: List<String>) {
        prefs(context).edit().putString(KLJUC, gson.toJson(seznam)).apply()
    }

    fun dodaj(context: Context, ime: String): Boolean {
        val seznam = naloziPriljubljene(context)
        if (seznam.contains(ime)) return false
        seznam.add(ime)
        shrani(context, seznam)
        return true
    }

    fun odstrani(context: Context, ime: String) {
        val seznam = naloziPriljubljene(context)
        seznam.remove(ime)
        shrani(context, seznam)
    }

    fun pocistiVse(context: Context) {
        prefs(context).edit().remove(KLJUC).apply()
    }
}
