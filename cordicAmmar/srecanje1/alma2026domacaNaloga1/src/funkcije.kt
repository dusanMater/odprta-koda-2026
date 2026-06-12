import kotlin.random.Random

fun funkcije() {
    pozdravi("Dušan")

    println("Kvadrat števila 4: " + kvadratStevila(4))
    println("Kvadrat števila -3: " + kvadratStevila(-3))

    println("Obseg kroga: " + obsegKroga(21.0))

    println("Ali je številka 4 soda? " + jeSodo(4))

    println("Katero število je večje, 10 ali 20? " + vecje(10, 20))

    println("Katero število je največje med 4,9 in 2? " + najvecje(4, 9, 2))

    println("Naključno število: " + nakljucnoStevilo())

    println("Funkcija združi niza Hello in World: " + zdruzi("Hello", "World"))

    pozdrav("Ana")
    pozdrav("Ana", 10)

}


// =========================
// FUNKCIJE
// =========================

fun pozdravi(ime: String) {
    println("Pozdravljen, $ime")
}

fun kvadratStevila(x: Int): Int {
    return x * x
}

fun obsegKroga(r: Double): Double {
    return 2 * 3.14 * r
}

fun jeSodo(x: Int): Boolean {
    return x % 2 == 0
}

fun vecje(a: Int, b: Int): Int {
    return maxOf(a, b)
}

fun najvecje(a: Int, b: Int, c: Int): Int {
    return maxOf(a, b, c)
}

fun nakljucnoStevilo(): Int {
    return Random.nextInt(1, 101)
}

fun zdruzi(a: String, b: String): String {
    return "$a $b"
}

fun pozdrav(ime: String, ura: Int = 0) {
    if (ura != 0) {
        println("Pozdrav $ime, ura je $ura")
    } else {
        println("Pozdrav $ime")
    }
}