fun pogojniStavkiInZanke() {
    // 1
    val star = 24
    println("Starost: $star")
    if (star >= 18) {
        println("Polnoleten")
    } else {
        println("Mladoleten")
    }

    // 2
    val dan = 6
    println("Dan v tednu: $dan")
    when (dan) {
        1 -> println("Ponedeljek")
        2 -> println("Torek")
        3 -> println("Sreda")
        4 -> println("Četrtek")
        5 -> println("Petek")
        6 -> println("Sobota")
        7 -> println("Nedelja")
        else -> println("Neveljavno")
    }

    // 3
    val st = -5
    println("Številka: $st")
    when {
        st > 0 -> println("Pozitivno")
        st < 0 -> println("Negativno")
        else -> println("0")
    }

    // 4
    print("Števila 1-10: ")
    for (i in 1..10) {
        if (i != 10) {
            print("$i, ")
        } else {
            println(i)
        }
    }

    // 5
    print("While zanka šteje do 5: ")
    var counter = 1
    while (counter <= 5) {
        if (counter != 5) {
            print("$counter, ")
        } else {
            println(counter)
        }
        counter++
    }

    // 6
    var vsota = 0
    for (i in 1..100) {
        vsota += i
    }
    println("Seštevek vseh števil med 1 in 100: $vsota")

    // 7
    var count = 0
    for (i in 1..50) {
        if (i % 3 == 0) {
            count++
        }
    }
    println("Število števil deljivih s 3 v intervalu 1-50: $count")

    // 8
    println("Break prekine zanko, ko pride do števila 5")
    for (i in 1..10) {
        if (i == 5) break
        print("$i, ")
    }

    // 9
    println("\nContinue preskoči številko 5")
    for (i in 1..10) {
        if (i == 5) continue
        print("$i, ")
    }

    // 10
    val imena = listOf("Ana", "Marko", "Eva")
    println("\nImena: ")
    for (imee in imena) {
        print("$imee, ")
    }


}