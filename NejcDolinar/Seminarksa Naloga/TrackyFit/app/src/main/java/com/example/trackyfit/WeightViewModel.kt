package com.example.trackyfit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.mutableStateListOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WeightViewModel(application: Application) : AndroidViewModel(application) {

    private val storage = WeightStorage(application)

    val entries = mutableStateListOf<WeightEntry>()

    init {
        entries.addAll(storage.loadEntries())
    }

    fun addEntry(weight: Double) {
        val entry = WeightEntry(
            weight = weight,
            date = getCurrentDate()
        )
        entries.add(entry)
        storage.addEntry(entry)
    }

    fun deleteEntry(id: Long) {
        entries.removeIf { it.id == id }
        storage.deleteEntry(id)
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return sdf.format(Date())
    }

    // Trend — razlika med zadnjima dvema vnosoma
    fun getTrend(): String {
        if (entries.size < 2) return ""
        val last = entries[entries.size - 1].weight
        val prev = entries[entries.size - 2].weight
        val diff = last - prev
        return when {
            diff > 0 -> "+${String.format("%.1f", diff)} kg ↑"
            diff < 0 -> "${String.format("%.1f", diff)} kg ↓"
            else -> "= ni spremembe"
        }
    }
}