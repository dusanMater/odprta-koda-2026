fun operatorji() {
    println("\nAritmetični operatorji")
    println("-".repeat(30))

    // 1
    println("Sestevek stevil 5 in 3: " + 5 + 3)

    // 2
    val num = 7
    if (num % 2 == 0) {
        println("Stevilo je sodo")
    } else {
        println("Stevilo je liho")
    }

    // 3
    val s1 = 5
    val s2 = 7
    val s3 = 9
    val avg = (s1 + s2 + s3).toDouble() / 3
    println("Povprecje za stevila $s1, $s2 in $s3 je: $avg")

    // 4
    print("Dolžina: ")
    val dolzina = readln().toDouble()

    print("Širina: ")
    val sirina = readln().toDouble()

    println("Površina pravokotnika je: ${dolzina * sirina}")

    // 5
    print("Vpiši temperaturo v celzijih: ")
    val celsius = readln().toDouble()
    val fahrenheit = (celsius * 1.8) + 32
    println("Vnešena temperatura v celzijih je: $fahrenheit stopinj Farenheitov")

    // 6
    val rezultat = 10.0 / 3.0
    println("Če delimo 10 z 3 dobimo: " + String.format("%.2f", rezultat))

    // 7
    var ink = 5
    println("Nastavljeno število: $ink")
    ink++
    println("Vrednost števila po inkrementu: $ink")
    ink--
    println("Vrednost števila po dekrementu: $ink")

    // 8
    val kvadrat = 6 * 6
    println("Kvadrat števila 6 je: $kvadrat")

    // 9
    println("Ostanek pri deljenju 10 z 3 je: " + 10 % 3)

    // 10
    val doubleRezultat = 12.99
    val intRezultat = doubleRezultat.toInt()
    println("Celoštevilska vrednost double števila 12.99 je: $intRezultat")


    println("\nPrimerjalni operatorji")
    println("-".repeat(30))

    // 1
    val x = 10
    val y = 20
    println("x = $x\ny = $y")

    if (x > y) {
        println("x je večji")
    } else {
        println("y je večji")
    }

    // 2
    println("x == y? " + (x == y))

    // 3
    val starost = 24
    if (starost >= 18) {
        println("Polnoleten")
    }

    // 4
    val niz1 = "Kotlin"
    val niz2 = "Java"
    println("niz1: $niz1 in niz2: $niz2")
    println("Ali je niz1 daljši kot niz2? " + (niz1.length > niz2.length))

    // 5
    val podatek = 5
    println("Ali je podatek različen od 0? " + (podatek != 0))

    // 6
    println("Ali je abc == abc? " + ("abc" == "abc"))

    // 7
    val a = 1
    val b = 2
    val c = 3
    println("a = $a, b = $b in c = $c")
    println("Ali je a < b in b < c? " + (a < b && b < c))

    // 8
    val score = 95
    if (score in 90..100) {
        println("Odlično")
    }

    // 9
    println("Ali je a < z? " + ('a' < 'z'))

    // 10
    val datum1 = "7.5.2026"
    val datum2 = "9.5.2026"
    println("datum1 = $datum1 in datum2 = $datum2")
    println("Ali je datum1 > datum2" + (datum1 > datum2))


    println("\nLogični operatorji")
    println("-".repeat(30))

    // 1
    val stev = 30
    println("Število: $stev")
    println("Ali je število deljivo s 3 in z 11? " + (stev % 3 == 0 && stev % 11 == 0))

    // 2
    println("Ali je število deljivo s 3 ali z 11? " + (stev % 3 == 0 || stev % 11 == 0))

    // 3
    val jeDezevno = false
    println("Ali je dezevno: $jeDezevno")
    println("Ali ni deževno? " + !jeDezevno)

    // 4
    val leta = 30
    println("Leta: $leta")
    if (leta > 18 && leta < 65) {
        println("Delovno aktiven")
    }

    // 5
    val temp = 25
    println("Temp: $temp")
    println("Ali je temperatura med 20 in 30 stopinj ali je 35 stopinj? " + (temp > 20 && temp < 30 || temp == 35))

    // 6
    val znak = '5'
    println("Znak: $znak")
    println("Ali znak NI črka? " + !znak.isLetter())

    // 7
    val prvi = ""
    val drugi = "test"
    println("Prvi: $prvi in drugi: $drugi")
    println("Ali je vsaj eden od teh nizov prazen? " + (prvi.isEmpty() || drugi.isEmpty()))

    // 8
    println("Ali je številka 8 pozitivna in soda? " + jePozitivnoInSodo(8))

    // 9
    val admin = true
    val prijavljen = false
    println("Admin: $admin in prijavljen: $prijavljen")
    println("Ali je uporabnik admin in prijavljen? " + (admin && prijavljen))

    // 10
    println("!(5 > 10)? " + !(5 > 10))
    println("Razlaga: \nprogram prvo preveri ali je 5 večje kot 10 in dobi da ni, potem pa doda negacijo pred to in se false pretvori v true")

}

fun jePozitivnoInSodo(x: Int): Boolean {
    return x > 0 && x % 2 == 0
}