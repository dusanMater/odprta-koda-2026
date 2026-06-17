package poglavje3

/**
 * 3. Pogojni stavki in zanke
 */
fun main() {
    println("=== 3. Pogojni stavki in zanke ===\n")

    // 1. if-else - polnoletnost
    val starost = 17
    if (starost >= 18) println("1. Polnoleten") else println("1. Mladoleten")

    // 2. when - dan v tednu
    val dan = 3
    val imeDneva = when (dan) {
        1 -> "Ponedeljek"
        2 -> "Torek"
        3 -> "Sreda"
        4 -> "Četrtek"
        5 -> "Petek"
        6 -> "Sobota"
        7 -> "Nedelja"
        else -> "Neveljaven dan"
    }
    println("2. Dan številka $dan = $imeDneva")

    // 3. Pozitivno / negativno / 0
    val st = -5
    when {
        st > 0 -> println("3. $st je pozitivno")
        st < 0 -> println("3. $st je negativno")
        else -> println("3. $st je nič")
    }

    // 4. for zanka - števila od 1 do 10
    print("4. ")
    for (i in 1..10) print("$i ")
    println()

    // 5. while zanka - šteje do 5
    print("5. ")
    var i = 1
    while (i <= 5) {
        print("$i ")
        i++
    }
    println()

    // 6. Vsota vseh števil med 1 in 100
    var vsota = 0
    for (n in 1..100) vsota += n
    println("6. Vsota 1..100 = $vsota")
    //   alternativa: val vsota = (1..100).sum()

    // 7. Števila deljiva s 3 v intervalu
    val zacetek = 10
    val konec = 50
    var stevec = 0
    for (n in zacetek..konec) if (n % 3 == 0) stevec++
    println("7. Števil deljivih s 3 v [$zacetek, $konec]: $stevec")

    // 8. break - prekinitev zanke
    print("8. (break pri 7) ")
    for (n in 1..20) {
        if (n == 7) break
        print("$n ")
    }
    println()

    // 9. continue - preskok vrednosti (izpiše samo liha)
    print("9. (continue za soda) ")
    for (n in 1..10) {
        if (n % 2 == 0) continue
        print("$n ")
    }
    println()

    // 10. for-each po seznamu imen
    val imena = listOf("Jure", "Boris", "Janez", "Damir")
    println("10. Imena:")
    imena.forEach { println("   - $it") }
}

main()