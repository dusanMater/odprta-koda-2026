package com.example.izpitna

data class Bencinska(
    val ime: String,
    val koda: String,
    val valuta: String,
    val enota: String,
    val cenaDizla: Double?,
    val cenaBencina: Double?
)

data class Shranjena(
    val ime: String,
    val koda: String,
    val valuta: String,
    val enota: String,
    val cenaDizla: Double?,
    val cenaBencina: Double?
)

fun kodaVZastavo(koda: String): String {
    if (koda.length != 2) return "🏳️"
    val first = 0x1F1E6 + (koda[0].uppercaseChar() - 'A')
    val second = 0x1F1E6 + (koda[1].uppercaseChar() - 'A')
    return String(intArrayOf(first, second), 0, 2)
}