package com.example.trackyfit

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WorkoutStorage(context: Context) {

    // SharedPrefrences
    private val prefs = context.getSharedPreferences("trackyfit_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val KEY = "workouts"

    // Shranimo seznam teningov kot JSON String
    fun saveWorkouts(workouts: List<Workout>) {
        val json = gson.toJson(workouts)
        prefs.edit().putString(KEY, json).apply()
    }

    // Naložimo seznam treningov iz JSON stringa
    fun loadWorkouts(): List<Workout> {
        val json = prefs.getString(KEY, null) ?: return emptyList()
        val type = object : TypeToken<List<Workout>>() {}.type
        return gson.fromJson(json, type)
    }

    // Dodaj Trening
    fun addWorkout(workout: Workout) {
        val workouts = loadWorkouts().toMutableList()
        workouts.add(workout)
        saveWorkouts(workouts)
    }

    // Zbrišemo trening po ID
    fun deleteWorkout(id: Long) {
        val workouts = loadWorkouts().toMutableList()
        workouts.removeIf { it.id == id }
        saveWorkouts(workouts)
    }
}