package com.example.trackyfit

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeightStorage(context: Context) {

    private val prefs = context.getSharedPreferences("weight_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val KEY = "weight_entries"

    fun saveEntries(entries: List<WeightEntry>) {
        val json = gson.toJson(entries)
        prefs.edit().putString(KEY, json).apply()
    }

    fun loadEntries(): List<WeightEntry> {
        val json = prefs.getString(KEY, null) ?: return emptyList()
        val type = object : TypeToken<List<WeightEntry>>() {}.type
        return gson.fromJson(json, type)
    }

    fun addEntry(entry: WeightEntry) {
        val entries = loadEntries().toMutableList()
        entries.add(entry)
        saveEntries(entries)
    }

    fun deleteEntry(id: Long) {
        val entries = loadEntries().toMutableList()
        entries.removeIf { it.id == id }
        saveEntries(entries)
    }
}