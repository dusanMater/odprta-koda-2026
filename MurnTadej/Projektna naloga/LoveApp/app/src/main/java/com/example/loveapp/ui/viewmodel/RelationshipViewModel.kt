package com.example.loveapp.ui.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.loveapp.data.PreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

// Vse podatke, ki jih UI potrebuje, združimo v en data class
data class RelationshipState(
    val myName: String = "",
    val partnerName: String = "",
    val startDate: Long = 0L,
    val photoPath: String = "",
    val setupDone: Boolean = false,
    val notificationsEnabled: Boolean = true,
    val isLoaded: Boolean = false   // za loading state med branjem iz DataStore
)

class RelationshipViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = PreferencesManager(application.applicationContext)

    // Združimo vseh 6 Flows iz PreferencesManager v en sam StateFlow
    val state: StateFlow<RelationshipState> = combine(
        prefs.myName,
        prefs.partnerName,
        prefs.startDate,
        prefs.photoPath,
        prefs.setupDone,
        prefs.notificationsEnabled
    ) { array ->
        RelationshipState(
            myName = array[0] as String,
            partnerName = array[1] as String,
            startDate = array[2] as Long,
            photoPath = array[3] as String,
            setupDone = array[4] as Boolean,
            notificationsEnabled = array[5] as Boolean,
            isLoaded = true
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = RelationshipState()
    )

    // ===== Akcije ki jih UI lahko sproži =====

    // Welcome screen — shrani vse naenkrat in označi setup kot končan
    fun saveSetup(
        myName: String,
        partnerName: String,
        startDate: Long,
        photoUri: Uri?
    ) {
        viewModelScope.launch {
            val savedPath = photoUri?.let { copyImageToInternalStorage(it) } ?: ""

            prefs.saveMyName(myName)
            prefs.savePartnerName(partnerName)
            prefs.saveStartDate(startDate)
            prefs.savePhotoPath(savedPath)
            prefs.setSetupDone(true)
        }
    }

    // Settings screen — posamezne posodobitve
    fun updateMyName(name: String) {
        viewModelScope.launch { prefs.saveMyName(name) }
    }

    fun updatePartnerName(name: String) {
        viewModelScope.launch { prefs.savePartnerName(name) }
    }

    fun updateStartDate(date: Long) {
        viewModelScope.launch { prefs.saveStartDate(date) }
    }

    fun updatePhoto(uri: Uri) {
        viewModelScope.launch {
            val path = copyImageToInternalStorage(uri)
            prefs.savePhotoPath(path)
        }
    }

    fun toggleNotifications(enabled: Boolean) {
        viewModelScope.launch { prefs.setNotificationsEnabled(enabled) }
    }

    // Helper: skopira sliko iz galerije v aplikacijino zasebno shrambo
    private suspend fun copyImageToInternalStorage(uri: Uri): String = withContext(Dispatchers.IO) {
        val context = getApplication<Application>().applicationContext
        val fileName = "couple_photo_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        context.contentResolver.openInputStream(uri)?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        file.absolutePath
    }

    fun resetAll() {
        viewModelScope.launch {
            prefs.resetAll()
        }
    }
}