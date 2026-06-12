fun main() {
    val kviz = Kviz()
    val maxVprasanj = kviz.velikostNabora()

    println("--- Mini kviz ---")
    println("V naboru je $maxVprasanj vprašanj.")

    // Vnos števila vprašanj
    var n = 0
    while (n !in 1..maxVprasanj) {
        print("Koliko vprašanj želiš odgovarjati (1-$maxVprasanj)? ")
        n = readLine()?.toIntOrNull() ?: 0
        if (n !in 1..maxVprasanj) {
            println("Vnesi število med 1 in $maxVprasanj.")
        }
    }

    // Izberi naključna vprašanja in jih izpostavi uporabniku
    val izbrana = kviz.izberi(n)
    var tocke = 0

    for (i in izbrana.indices) {
        val vprasanje = izbrana[i]
        println("\nVprašanje ${i + 1}/$n: ${vprasanje.text}")
        print("Tvoj odgovor: ")
        val odgovor = readLine() ?: ""

        if (kviz.preveri(vprasanje, odgovor)) {
            println("Pravilno!")
            tocke++
        }
        else {
            println("Napačno! Pravilen odgovor: ${vprasanje.answer}")
        }
    }

    // Povzetek na koncu
    println("\n--- Rezultat ---")
    println("Pravilnih odgovorov: $tocke/$n")
}
