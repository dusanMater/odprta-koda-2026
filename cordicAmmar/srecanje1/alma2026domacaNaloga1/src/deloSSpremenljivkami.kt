import kotlin.math.roundToInt

fun deloSSpremenljivkami() {
    // 1
    var ime: String = "Ammar"
    println("Moje ime je: $ime")

    // 2
    var letoRojstva: Int = 2002
    val trenutnoLeto = 2026
    val starost = trenutnoLeto - letoRojstva
    println("Starost: $starost")

    // 3
    val starostDouble: Double = starost.toDouble()
    println("Starost v double tipu: $starostDouble")

    // 4
    val drzava = "Slovenija"
    // drzava = "Hrvaška" // ERROR - val ni mogoče spreminjati

    // 5
    var stevilo = 10
    stevilo = 20
    println("Var število: $stevilo")

    val konstanta = 100
    println("Val konstanta: $konstanta")

    // 6
    val cena: Float = 19.99f
    println("Cela vrednost cene: " + cena.roundToInt())

    // 7
    val a = 10
    val b = 20
    val c = 30
    val povprecje = (a + b + c) / 3.0
    println("Povprecje za stevila $a, $b in $c je: $povprecje")

    // 8
    print("Vpiši ime: ")
    val uporabnikIme = readln()
    println("Tvoje ime je $uporabnikIme")

    // 9
    println("Dolžina: ${uporabnikIme.length}")

    // 10
    println("Prvi znak: ${uporabnikIme[0]}")

}