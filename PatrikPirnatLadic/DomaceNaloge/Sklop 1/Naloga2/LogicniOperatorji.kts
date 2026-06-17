package poglavje2

/**
 * 2.3 Logični operatorji (&&, ||, !)
 */
fun main() {
    println("=== 2.3 Logični operatorji ===\n")

    // 1. && - deljivo s 3 IN s 11
    val n1 = 33
    println("1. $n1 deljivo s 3 IN 11 -> ${n1 % 3 == 0 && n1 % 11 == 0}")

    // 2. || - deljivo s 3 ALI s 11
    val n2 = 22
    println("2. $n2 deljivo s 3 ALI 11 -> ${n2 % 3 == 0 || n2 % 11 == 0}")

    // 3. ! - negacija pogoja
    val pravilno = true
    println("3. !pravilno -> ${!pravilno}")

    // 4. starost med 18 in 65 -> "Delovno aktiven"
    val starost = 35
    val delovnoAktiven = starost in 18..65
    if (delovnoAktiven) println("4. Delovno aktiven") else println("4. Ni delovno aktiven")

    // 5. Kombinacija && in || (sme voziti, če je polnoleten IN ima izpit ALI vozilo)
    val leta = 25
    val imaIzpit = true
    val imaVozilo = false
    if (leta >= 18 && (imaIzpit || imaVozilo)) println("5. Lahko vozi")
    else println("5. Ne sme voziti")

    // 6. Znak NI črka
    val znak = '7'
    println("6. Znak '$znak' NI črka -> ${!znak.isLetter()}")

    // 7. Vsaj eden izmed dveh nizov prazen
    val a = ""
    val b = "nekaj"
    println("7. vsaj eden prazen -> ${a.isEmpty() || b.isEmpty()}")

    // 8. Funkcija: pozitivno in sodo
    println("8. jePozitivnoInSodo(4)  -> ${jePozitivnoInSodo(4)}")
    println("   jePozitivnoInSodo(-2) -> ${jePozitivnoInSodo(-2)}")
    println("   jePozitivnoInSodo(7)  -> ${jePozitivnoInSodo(7)}")

    // 9. Logični pogoji z Boolean spremenljivkami
    val deluje = true
    val poln = false
    println("9. deluje && !poln -> ${deluje && !poln}")

    // 10. if (!(a > b))
    //   Razlaga: pogoj je resničen, kadar a NI večji od b - torej a <= b.
    val aa = 5
    val bb = 10
    if (!(aa > bb)) {
        println("10. NI res, da je $aa > $bb (torej $aa <= $bb)")
    }
}

fun jePozitivnoInSodo(n: Int): Boolean = n > 0 && n % 2 == 0

main()