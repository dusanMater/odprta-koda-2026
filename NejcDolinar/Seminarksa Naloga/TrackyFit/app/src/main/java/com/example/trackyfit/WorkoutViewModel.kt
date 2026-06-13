package com.example.trackyfit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {
    // Pomoć za Storage
    private val storage = WorkoutStorage(application)

    // Seznam vseh trenigov ki ga Compose opazuje
    val workouts = mutableStateListOf<Workout>()

    // Ko se zažene aplikacije naloži shranjene treninge
    init {
        workouts.addAll(storage.loadWorkouts())
    }

    // Dodaj nov trening
    fun addWorkout(name: String, sets: Int, reps: Int, weight: Double) {
        val workout = Workout(
            name = name,
            sets = sets,
            reps = reps,
            weight = weight,
            date = getCurrentDate()
        )
        workouts.add(workout)
        storage.addWorkout(workout)
    }

    // Zbriši trening
    fun deleteWorkout(id: Long) {
        workouts.removeIf { it.id == id }
        storage.deleteWorkout(id)
    }

    // Pretvori trenuti datum v string
    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return sdf.format(Date())
    }
}