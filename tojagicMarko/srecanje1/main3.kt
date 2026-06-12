import kotlin.random.Random

data class Question(val text: String, val answer: String)

fun main() {
    val questions = listOf(
        Question("Glavno mesto Slovenije?", "Ljubljana"),
        Question("Koliko je 5 + 7?", "12"),
        Question("Kateri jezik se uporablja za Android?", "Kotlin")
    )
    println("MINI KVIZ")
    var tocke = 0
    var stVprasanj = 0

    while (true) {
        val q = questions.random()
        println("Vprašanje: ${q.text}")
        print("Tvoj odgovor: ")
        val userAnswer = readlnOrNull()?.trim() ?: ""

        if (userAnswer.equals("konec", ignoreCase = true)) {
            println("Konec kviza")
            break
        }

        if (userAnswer.lowercase() == q.answer.lowercase()) {
            println("Pravilno!")
            tocke++
        } else {
            println("Napačno. Pravilen odgovor je: ${q.answer}")
        }
        stVprasanj++
        println()
    }
    println("Pravilnih: $tocke od $stVprasanj")
    if (stVprasanj > 0) {
        println("Uspešnost: ${tocke * 100 / stVprasanj}%")
    }
}