import kotlin.random.Random

data class Question(
    val vprasanje: String,
    val odgovor: String
)

fun main() {

    val vprasanja = listOf(
        Question("Glavno mesto Slovenije?", "Ljubljana"),
        Question("Koliko je 5 + 7?", "12"),
        Question("Kateri jezik se uporablja za Android?", "Kotlin")
    )

    val izbrano = vprasanja[Random.nextInt(vprasanja.size)]

    println(izbrano.vprasanje)

    val userOdgovor = readln()

    if (userOdgovor.lowercase() == izbrano.odgovor.lowercase()) {
        println("Pravilno!")
    } else {
        println("Napačno! Pravilen odgovor je: ${izbrano.odgovor}")
    }
}