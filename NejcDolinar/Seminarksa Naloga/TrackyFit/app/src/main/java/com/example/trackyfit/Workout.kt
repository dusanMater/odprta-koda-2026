package com.example.trackyfit

data class Workout(
    val id: Long = System.currentTimeMillis(),
    val name: String,
    val sets: Int,
    val reps: Int,
    val weight: Double,
    val date: String
)