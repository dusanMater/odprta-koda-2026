package projekti

import kotlin.random.Random

/**
 * Projekt 3: Mini kviz
 *
 * Seznam vprašanj, naključno izbrano vprašanje, preverjanje odgovora
 * (neobčutljivo na velikost črk). Igramo dokler uporabnik ne vpiše 'q'.
 */

data class Question(
    val text: String,
    val answer: String
)

val questions = listOf(
    Question("Glavno mesto Slovenije?", "Ljubljana"),
    Question("Koliko je 5 + 7?", "12"),
    Question("Kateri jezik se uporablja za Android?", "Kotlin")
)

fun main() {
    println("=== Mini kviz ===")
    println("Odgovarjaj na vprašanja. Vpiši 'q' za izhod.\n")

    var tocke = 0
    var skupaj = 0

    while (true) {
        val vprasanje = questions[Random.nextInt(questions.size)]
        println("Vprašanje: ${vprasanje.text}")
        print("Odgovor: ")
        val odgovor = readLine()?.trim().orEmpty()

        if (odgovor.equals("q", ignoreCase = true)) break
        if (odgovor.isEmpty()) {
            println("(prazen odgovor - preskočeno)\n")
            continue
        }

        skupaj++
        if (odgovor.equals(vprasanje.answer, ignoreCase = true)) {
            println("Pravilno!")
            tocke++
        } else {
            println("Napačno. Pravilen odgovor: ${vprasanje.answer}")
        }
        println("Trenutni rezultat: $tocke / $skupaj\n")
    }

    println("\n=== Konec kviza ===")
    if (skupaj == 0) {
        println("Brez odgovorov.")
    } else {
        val odstotek = (tocke * 100.0 / skupaj)
        println("Končni rezultat: $tocke / $skupaj  (${"%.1f".format(odstotek)} %)")
        val ocena = when {
            odstotek >= 90 -> "Odlično!"
            odstotek >= 75 -> "Prav dobro."
            odstotek >= 50 -> "Dobro."
            else -> "Še malo treninga..."
        }
        println(ocena)
    }
}

main()