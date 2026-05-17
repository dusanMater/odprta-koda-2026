import kotlin.math.PI
import kotlin.math.roundToInt
import kotlin.random.Random

fun main() {
    println("===== RESITVE PROGRAMSKIH NALOG V KOTLINU =====")

    deloSSpremenljivkami()
    operatorjiAritmeticni()
    operatorjiPrimerjalni()
    operatorjiLogicni()
    pogojniStavkiInZanke()
    funkcijePrimeri()
    razrediPrimeri()
    seznamiPrimeri()

    projektNakupovalniSeznam()
    projektGeneratorGesel()
    projektMiniKviz()
}

fun deloSSpremenljivkami() {
    println()
    println("===== 1. DELO S SPREMENLJIVKAMI =====")

    val ime: String = "Tilen"
    println("1. Ime: $ime")

    val letoRojstva: Int = 2006
    val trenutnoLeto = 2026
    val starost = trenutnoLeto - letoRojstva
    println("2. Starost: $starost")

    val starostDouble: Double = starost.toDouble()
    println("3. Starost kot Double: $starostDouble")

    val nespremenljiva = 10
    println("4. val vrednost: $nespremenljiva")
    println("   val spremenljivke ne moremo spremeniti - dobimo napako pri prevajanju.")

    val valPrimer = "To je val"
    var varPrimer = "To je var"
    varPrimer = "var lahko spremenimo"
    println("5. val: $valPrimer")
    println("   var: $varPrimer")

    val cena: Float = 12.7f
    val zaokrozenaCena = cena.roundToInt()
    println("6. Cena: $cena, zaokrozena: $zaokrozenaCena")

    val a = 10
    val b = 20
    val c = 30
    val povprecje = (a + b + c) / 3.0
    println("7. Povprecje treh stevil: $povprecje")

    print("8. Vpisi ime: ")
    val vpisanoIme = readlnOrNull() ?: ""
    println("Tvoje ime je $vpisanoIme.")

    print("9. Vpisi besedo ali stavek: ")
    val beseda = readlnOrNull() ?: ""
    println("Dolzina vpisanega niza je: ${beseda.length}")

    println("10. Prvi znak iz niza '$ime' je: ${ime[0]}")
}

fun operatorjiAritmeticni() {
    println()
    println("===== 2. OPERATORJI - ARITMETICNI =====")

    val a = 15
    val b = 4

    println("1. Vsota $a + $b = ${a + b}")

    val stevilo = 12
    if (stevilo % 2 == 0) {
        println("2. Stevilo $stevilo je sodo.")
    } else {
        println("2. Stevilo $stevilo je liho.")
    }

    val x = 5
    val y = 8
    val z = 10
    val povprecje = (x.toDouble() + y.toDouble() + z.toDouble()) / 3.0
    println("3. Povprecje stevil $x, $y in $z je $povprecje")

    print("4. Vpisi dolzino pravokotnika: ")
    val dolzina = readlnOrNull()?.toDoubleOrNull() ?: 0.0
    print("   Vpisi sirino pravokotnika: ")
    val sirina = readlnOrNull()?.toDoubleOrNull() ?: 0.0
    val povrsina = dolzina * sirina
    println("   Povrsina pravokotnika je: $povrsina")

    val celzij = 25.0
    val fahrenheit = celzij * 9.0 / 5.0 + 32.0
    println("5. $celzij C je $fahrenheit F")

    val rezultat = 10.0 / 3.0
    println("6. Rezultat deljenja: %.2f".format(rezultat))

    var stev = 5
    stev++
    println("7. Po inkrementu: $stev")
    stev--
    println("   Po dekrementu: $stev")

    val kvadratStevila = 7
    println("8. Kvadrat stevila $kvadratStevila je ${kvadratStevila * kvadratStevila}")

    println("9. Ostanek pri deljenju $a % $b = ${a % b}")

    val doubleRezultat = 9.87
    val intRezultat = doubleRezultat.toInt()
    println("10. Double $doubleRezultat pretvorjen v Int je $intRezultat")
}

fun operatorjiPrimerjalni() {
    println()
    println("===== 2. OPERATORJI - PRIMERJALNI =====")

    val a = 10
    val b = 20

    if (a > b) {
        println("1. Vecje je stevilo $a.")
    } else if (b > a) {
        println("1. Vecje je stevilo $b.")
    } else {
        println("1. Stevili sta enaki.")
    }

    val x = 5
    val y = 5
    println("2. Ali je x == y? ${x == y}")

    val starost = 19
    if (starost >= 18) {
        println("3. polnoleten")
    }

    val niz1 = "Kotlin"
    val niz2 = "Programiranje"
    println("4. Dolzina '$niz1' je ${niz1.length}, dolzina '$niz2' je ${niz2.length}")
    println("   Ali je prvi niz daljsi? ${niz1.length > niz2.length}")

    val podatek = 15
    println("5. Ali je podatek razlicen od nic? ${podatek != 0}")

    val beseda1 = "Ana"
    val beseda2 = "Ana"
    println("6. Ali sta niza enaka? ${beseda1 == beseda2}")

    val c = 30
    println("7. Ali velja a < b in b < c? ${a < b && b < c}")

    val score = 95
    if (score in 90..100) {
        println("8. Odlicno")
    }

    println("9. Ali je 'a' < 'z'? ${'a' < 'z'}")

    val datum1 = "2026-05-17"
    val datum2 = "2025-12-31"
    println("10. Primerjava datumov: datum1 > datum2 je ${datum1 > datum2}")
}

fun operatorjiLogicni() {
    println()
    println("===== 2. OPERATORJI - LOGICNI =====")

    val stevilo = 33
    println("1. Ali je $stevilo deljivo s 3 in 11? ${stevilo % 3 == 0 && stevilo % 11 == 0}")

    val drugoStevilo = 22
    println("2. Ali je $drugoStevilo deljivo s 3 ali 11? ${drugoStevilo % 3 == 0 || drugoStevilo % 11 == 0}")

    val pogoj = true
    println("3. Negacija true je ${!pogoj}")

    val starost = 35
    val delovnoAktiven = starost >= 18 && starost <= 65
    if (delovnoAktiven) {
        println("4. Delovno aktiven")
    }

    val imaVozniskiIzpit = true
    val imaAvto = false
    val imaKolo = true
    println("5. Lahko potuje? ${(imaVozniskiIzpit && imaAvto) || imaKolo}")

    val znak = '7'
    println("6. Ali znak '$znak' NI crka? ${!znak.isLetter()}")

    val prviNiz = ""
    val drugiNiz = "Kotlin"
    println("7. Ali je vsaj en niz prazen? ${prviNiz.isEmpty() || drugiNiz.isEmpty()}")

    println("8. Ali je 8 pozitivno in sodo? ${jePozitivnoInSodo(8)}")

    val jeDijak = true
    val imaNalogo = false
    println("9. jeDijak && !imaNalogo = ${jeDijak && !imaNalogo}")

    val x = 5
    val y = 10
    println("10. !(x > y) za x=$x in y=$y je: ${!(x > y)}")
    println("    To pomeni: x ni vecji od y.")
}

fun jePozitivnoInSodo(stevilo: Int): Boolean {
    return stevilo > 0 && stevilo % 2 == 0
}

fun pogojniStavkiInZanke() {
    println()
    println("===== 3. POGOJNI STAVKI IN ZANKE =====")

    val starost = 17
    if (starost >= 18) {
        println("1. Uporabnik je polnoleten.")
    } else {
        println("1. Uporabnik ni polnoleten.")
    }

    val dan = 3
    when (dan) {
        1 -> println("2. Ponedeljek")
        2 -> println("2. Torek")
        3 -> println("2. Sreda")
        4 -> println("2. Cetrtek")
        5 -> println("2. Petek")
        6 -> println("2. Sobota")
        7 -> println("2. Nedelja")
        else -> println("2. Neznan dan")
    }

    val stevilo = -5
    if (stevilo > 0) {
        println("3. Stevilo je pozitivno.")
    } else if (stevilo < 0) {
        println("3. Stevilo je negativno.")
    } else {
        println("3. Stevilo je 0.")
    }

    print("4. Stevila od 1 do 10: ")
    for (i in 1..10) {
        print("$i ")
    }
    println()

    print("5. While do 5: ")
    var i = 1
    while (i <= 5) {
        print("$i ")
        i++
    }
    println()

    var vsota = 0
    for (n in 1..100) {
        vsota += n
    }
    println("6. Vsota stevil od 1 do 100 je $vsota")

    var stevec = 0
    for (n in 1..50) {
        if (n % 3 == 0) {
            stevec++
        }
    }
    println("7. Stevil deljivih s 3 med 1 in 50 je $stevec")

    print("8. Break pri 6: ")
    for (n in 1..10) {
        if (n == 6) {
            break
        }
        print("$n ")
    }
    println()

    print("9. Continue - preskocimo 5: ")
    for (n in 1..10) {
        if (n == 5) {
            continue
        }
        print("$n ")
    }
    println()

    val imena = listOf("Ana", "Bine", "Cene")
    println("10. For-each za seznam imen:")
    for (ime in imena) {
        println(ime)
    }
}

fun funkcijePrimeri() {
    println()
    println("===== 4. FUNKCIJE =====")

    pozdravi("Ana")
    println("2. kvadrat(4) = ${kvadrat(4)}")
    println("   kvadrat(-3) = ${kvadrat(-3)}")
    println("3. Obseg kroga s polmerom 21 je ${obseg(21)}")
    println("4. Ali je 10 sodo? ${jeSodo(10)}")
    println("5. Vecje stevilo med 8 in 14 je ${vecjeStevilo(8, 14)}")
    println("6. Najvecje stevilo med 5, 9 in 3 je ${najvecjeOdTreh(5, 9, 3)}")
    println("7. Nakljucno stevilo: ${nakljucnoStevilo()}")
    println("8. Zdruzena niza: ${zdruziNiza("Dober", "dan")}")
    println("9. ${pozdrav("Ana", ura = 10)}")
    println("   ${pozdrav("Miha")}")
}

fun pozdravi(ime: String) {
    println("1. Pozdravljen, $ime")
}

fun kvadrat(stevilo: Int): Int {
    return stevilo * stevilo
}

fun obseg(polmer: Int): Double {
    return 2.0 * PI * polmer
}

fun jeSodo(stevilo: Int): Boolean {
    return stevilo % 2 == 0
}

fun vecjeStevilo(a: Int, b: Int): Int {
    if (a > b) {
        return a
    }
    return b
}

fun najvecjeOdTreh(a: Int, b: Int, c: Int): Int {
    var najvecje = a
    if (b > najvecje) {
        najvecje = b
    }
    if (c > najvecje) {
        najvecje = c
    }
    return najvecje
}

fun nakljucnoStevilo(): Int {
    return Random.nextInt(1, 101)
}

fun zdruziNiza(prvi: String, drugi: String): String {
    return "$prvi $drugi"
}

fun pozdrav(ime: String, ura: Int = 8): String {
    return "Pozdravljen, $ime. Ura je $ura."
}

fun razrediPrimeri() {
    println()
    println("===== 5. RAZREDI IN PODATKOVNI RAZREDI =====")

    val oseba1 = Oseba("Ana", 18)
    val oseba2 = Oseba("Bine", 20)
    val oseba3 = Oseba("Cene", 25)

    println("1. Razred Oseba z imenom in starostjo.")
    println("2. Klic metode predstaviSe():")
    oseba1.predstaviSe()
    oseba2.predstaviSe()
    oseba3.predstaviSe()

    println("3. Izpis treh oseb:")
    println(oseba1)
    println(oseba2)
    println(oseba3)

    println("4. toString() je poklicana pri println(oseba).")
}

fun seznamiPrimeri() {
    println()
    println("===== 6. POLJA, LISTI, SEZNAMI =====")

    val stevila = listOf(4, 8, 15, 16, 23, 42)
    println("1. List celih stevil: $stevila")

    val mutableStevila = mutableListOf(1, 2, 3)
    mutableStevila.add(4)
    println("2. Po dodajanju elementa: $mutableStevila")

    mutableStevila.remove(2)
    println("3. Po odstranitvi elementa 2: $mutableStevila")

    var najvecje = stevila[0]
    for (n in stevila) {
        if (n > najvecje) {
            najvecje = n
        }
    }
    println("4. Najvecje stevilo v seznamu je: $najvecje")

    println("5. Izpis vseh elementov:")
    for (n in stevila) {
        println(n)
    }

    val sodaStevilka = mutableListOf<Int>()
    for (n in stevila) {
        if (n % 2 == 0) {
            sodaStevilka.add(n)
        }
    }
    println("6. Sode stevilke: $sodaStevilka")

    val sortiranaStevila = stevila.sorted()
    println("7. Sortirana stevila: $sortiranaStevila")

    println("8. Ali seznam vsebuje stevilo 15? ${stevila.contains(15)}")

    val imena = listOf("Ana", "Bine", "Cene")
    val imenaKotString = imena.joinToString(", ")
    println("9. Imena kot en String: $imenaKotString")
}

fun projektNakupovalniSeznam() {
    println()
    println("===== PROJEKT 1: NAKUPOVALNI SEZNAM =====")

    val seznam = NakupovalniSeznam()

    seznam.dodajArtikel("Kruh")
    seznam.dodajArtikel("Mleko")
    seznam.dodajArtikel("Jabolka")

    seznam.izpisiSeznam()

    seznam.nastaviVKosarici("Mleko", true)
    seznam.spremeniNaziv("Jabolka", "Banane")

    println("Ali seznam vsebuje Kruh? ${seznam.vsebujeArtikel("Kruh")}")

    seznam.izbrisiArtikel("Kruh")

    seznam.izpisiSeznam()
}

fun projektGeneratorGesel() {
    println()
    println("===== PROJEKT 2: GENERATOR GESEL =====")

    print("Vpisi dolzino gesla: ")
    val dolzina = readlnOrNull()?.toIntOrNull() ?: 8

    print("Ali naj geslo vsebuje stevilke? da/ne: ")
    val useNumbers = readlnOrNull()?.lowercase() == "da"

    print("Ali naj geslo vsebuje posebne znake? da/ne: ")
    val useSymbols = readlnOrNull()?.lowercase() == "da"

    val pravilo = PasswordRule(
        length = dolzina,
        useNumbers = useNumbers,
        useSymbols = useSymbols
    )

    val geslo = generirajGeslo(pravilo)

    println("Generirano geslo: $geslo")
}

fun generirajGeslo(rule: PasswordRule): String {
    val maleCrke = "abcdefghijklmnopqrstuvwxyz"
    val velikeCrke = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val stevilke = "0123456789"
    val simboli = "!@#%&*?"

    var dovoljeniZnaki = maleCrke + velikeCrke

    if (rule.useNumbers) {
        dovoljeniZnaki += stevilke
    }

    if (rule.useSymbols) {
        dovoljeniZnaki += simboli
    }

    val builder = StringBuilder()

    for (i in 1..rule.length) {
        val nakljucniIndeks = Random.nextInt(dovoljeniZnaki.length)
        builder.append(dovoljeniZnaki[nakljucniIndeks])
    }

    return builder.toString()
}

fun projektMiniKviz() {
    println()
    println("===== PROJEKT 3: MINI KVIZ =====")

    val questions = listOf(
        Question("Glavno mesto Slovenije?", "Ljubljana"),
        Question("Koliko je 5 + 7?", "12"),
        Question("Kateri jezik se uporablja za Android?", "Kotlin"),
        Question("Koliko je 10 * 3?", "30"),
        Question("Katera funkcija izpise besedilo v Kotlinu?", "println")
    )

    val question = questions[Random.nextInt(questions.size)]

    println(question.text)
    print("Tvoj odgovor: ")
    val userAnswer = readlnOrNull() ?: ""

    if (userAnswer.lowercase() == question.answer.lowercase()) {
        println("Pravilno!")
    } else {
        println("Napacno. Pravilen odgovor je: ${question.answer}")
    }
}