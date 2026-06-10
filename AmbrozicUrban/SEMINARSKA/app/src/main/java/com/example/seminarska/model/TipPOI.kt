package com.example.seminarska.model

enum class TipPOI(val oznaka: String, val poizvedba: String) {
    KAVARNE("Kavarne", "cafe"),
    RESTAVRACIJE("Restavracije", "restaurant"),
    BANKE("Banke", "bank"),
    PARKI("Parki", "park"),
    BENCINSKE("Bencinske črpalke", "fuel")
}
