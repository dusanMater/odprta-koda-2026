package com.example.trackyfit

data class WeightEntry(
    val id: Long = System.currentTimeMillis(),
    val weight: Double,
    val date: String
)