package com.example.loveapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Top-level: doda lastnost 'dataStore' vsakemu Context objektu
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

class PreferencesManager(private val context: Context) {

    companion object {
        val MY_NAME = stringPreferencesKey("my_name")
        val PARTNER_NAME = stringPreferencesKey("partner_name")
        val START_DATE = longPreferencesKey("start_date")
        val PHOTO_PATH = stringPreferencesKey("photo_path")
        val SETUP_DONE = booleanPreferencesKey("setup_done")
        val NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
    }

    // ===== Branje (Flow) =====

    val myName: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[MY_NAME] ?: ""
    }

    val partnerName: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[PARTNER_NAME] ?: ""
    }

    val startDate: Flow<Long> = context.dataStore.data.map { prefs ->
        prefs[START_DATE] ?: 0L
    }

    val photoPath: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[PHOTO_PATH] ?: ""
    }

    val setupDone: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[SETUP_DONE] ?: false
    }

    val notificationsEnabled: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[NOTIFICATIONS_ENABLED] ?: true
    }

    // ===== Pisanje (suspend funkcije) =====

    suspend fun saveMyName(name: String) {
        context.dataStore.edit { prefs ->
            prefs[MY_NAME] = name
        }
    }

    suspend fun savePartnerName(name: String) {
        context.dataStore.edit { prefs ->
            prefs[PARTNER_NAME] = name
        }
    }

    suspend fun saveStartDate(date: Long) {
        context.dataStore.edit { prefs ->
            prefs[START_DATE] = date
        }
    }

    suspend fun savePhotoPath(path: String) {
        context.dataStore.edit { prefs ->
            prefs[PHOTO_PATH] = path
        }
    }

    suspend fun setSetupDone(done: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[SETUP_DONE] = done
        }
    }

    suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[NOTIFICATIONS_ENABLED] = enabled
        }
    }

    suspend fun resetAll() {
        // Zbriši shranjene slike (couple_photo_*)
        context.filesDir.listFiles()?.forEach { file ->
            if (file.name.startsWith("couple_photo_")) {
                file.delete()
            }
        }
        // Zbriši vse v DataStore
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}