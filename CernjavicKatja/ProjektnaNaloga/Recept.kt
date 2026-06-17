package model

data class Recept(
    val ime: String,
    val sestavine: String,
    val postopek: String,
    val casPriprave: String,
    val kategorija: String,
    val slikaUri: String
) {
    override fun toString(): String {
        return "$ime|$sestavine|$postopek|$casPriprave|$kategorija|$slikaUri"
    }
}