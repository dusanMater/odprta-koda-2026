package poglavje2

/**
 * 2.1 Aritmetični operatorji (+, -, *, /, %)
 */
fun main() {
    println("=== 2.1 Aritmetični operatorji ===\n")

    // 1. Seštevek dveh števil
    val a = 7
    val b = 13
    println("1. $a + $b = ${a + b}")

    // 2. Sodo / liho z uporabo %
    val st = 14
    println("2. Število $st je ${if (st % 2 == 0) "sodo" else "liho"}")

    // 3. Povprečje treh celih števil (pretvorba v Double)
    val x = 5
    val y = 8
    val z = 11
    val povp: Double = (x + y + z).toDouble() / 3
    println("3. Povprečje ($x, $y, $z) = $povp")

    // 4. Površina pravokotnika - vnos uporabnika
    print("4. Vpiši dolžino stranice a: ")
    val stranicaA = readLine()?.toDoubleOrNull() ?: 5.0
    print("   Vpiši dolžino stranice b: ")
    val stranicaB = readLine()?.toDoubleOrNull() ?: 3.0
    println("   Površina pravokotnika: ${stranicaA * stranicaB}")

    // 5. Celzij -> Fahrenheit
    val celzij = 25.0
    val fahrenheit = celzij * 9 / 5 + 32
    println("5. $celzij °C = $fahrenheit °F")

    // 6. Deljenje z dvema decimalnima mestoma
    val deljenec = 22
    val delitelj = 7
    val rezultat = deljenec.toDouble() / delitelj
    println("6. $deljenec / $delitelj = ${"%.2f".format(rezultat)}")

    // 7. Inkrement (++) in dekrement (--)
    var n = 10
    n++
    println("7a. Po n++ : $n")
    n--
    n--
    println("7b. Po dvojnem n--: $n")

    // 8. Kvadrat števila
    val k = 6
    println("8. Kvadrat $k = ${k * k}")

    // 9. Ostanek pri deljenju (modulo)
    val d1 = 25
    val d2 = 4
    println("9. $d1 % $d2 = ${d1 % d2}")

    // 10. Pretvorba Double -> Int (odreže decimalke)
    val realno = 12.87
    val celo: Int = realno.toInt()
    println("10. $realno kot Int = $celo")
}

main()