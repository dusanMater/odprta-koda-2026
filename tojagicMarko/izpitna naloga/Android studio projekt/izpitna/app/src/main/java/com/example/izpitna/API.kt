package com.example.izpitna

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

suspend fun dobiCene(drzava: String): List<Bencinska> = withContext(Dispatchers.IO) {
    dobiVse().filter { it.ime.equals(drzava, ignoreCase = true) }
}

suspend fun dobiVse(): List<Bencinska> = withContext(Dispatchers.IO) {
    val url = "https://openvan.camp/api/fuel/prices"
    return@withContext try {
        val conn = URL(url).openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.connectTimeout = 5000
        conn.readTimeout = 5000
        val odgovor = conn.inputStream.bufferedReader().readText()
        conn.disconnect()

        val gson = Gson()
        val tip = object : TypeToken<Map<String, Any>>() {}.type
        val odgovorMap: Map<String, Any> = gson.fromJson(odgovor, tip)
        val data = odgovorMap["data"] as? Map<*, *> ?: return@withContext emptyList()

        data.mapNotNull { (_, value) ->
            if (value is Map<*, *>) {
                val cene = value["prices"] as? Map<*, *>
                val enota = value["unit"] as? String ?: "liter"
                Bencinska(
                    ime = value["country_name"] as? String ?: return@mapNotNull null,
                    koda = value["country_code"] as? String ?: "",
                    valuta = value["currency"] as? String ?: "EUR",
                    enota = enota,
                    cenaDizla = (cene?.get("diesel") as? Number)?.toDouble(),
                    cenaBencina = (cene?.get("gasoline") as? Number)?.toDouble()
                )
            } else null
        }
    } catch (_: Exception) {
        emptyList()
    }
}

fun shraniPriljubljene(kon: Context, sez: List<Shranjena>) {
    val prefs = kon.getSharedPreferences("moja_app", Context.MODE_PRIVATE)
    val json = Gson().toJson(sez)
    prefs.edit().putString("priljubljene", json).apply()
}

fun naloziPriljubljene(kon: Context): List<Shranjena> {
    val prefs = kon.getSharedPreferences("moja_app", Context.MODE_PRIVATE)
    val json = prefs.getString("priljubljene", null) ?: return emptyList()
    val tip = object : TypeToken<List<Shranjena>>() {}.type
    return Gson().fromJson(json, tip)
}