package poglavje4

import kotlin.math.PI
import kotlin.random.Random

/**
 * 4. Funkcije (metode, podprogrami)
 */
fun main() {
    println("=== 4. Funkcije ===\n")

    // 1. pozdravi
    pozdravi("Nejc")

    // 2. kvadrat
    println("2. kvadrat(4) = ${kvadrat(4)}, kvadrat(-3) = ${kvadrat(-3)}")

    // 3. obseg kroga
    println("3. obseg(21.0) = ${obseg(21.0)}")

    // 4. jeSodo
    println("4. jeSodo(8) = ${jeSodo(8)}, jeSodo(9) = ${jeSodo(9)}")

    // 5. večje od dveh
    println("5. vecje(10, 20) = ${vecje(10, 20)}")

    // 6. največje od treh
    println("6. najvecje(5, 12, 7) = ${najvecje(5, 12, 7)}")

    // 7. naključno število
    println("7. nakljucno() = ${nakljucno()}")

    // 8. združitev dveh nizov
    println("8. zdruzi(\"Pozdravljen\", \"svet\") = \"${zdruzi("Pozdravljen", "svet")}\"")

    // 9. neobvezni parameter
    pozdrav("Jure")           // privzeta ura = 12
    pozdrav("Boris", ura = 9)
    pozdrav("Janez", ura = 20)
}

// 1.
fun pozdravi(ime: String) {
    println("1. Pozdravljen, $ime")
}

// 2. Kvadrat - deluje tudi za negativna števila
fun kvadrat(n: Int): Int = n * n

// 3. Obseg kroga: o = 2 * pi * r
fun obseg(r: Double): Double = 2 * PI * r

// 4. Ali je število sodo
fun jeSodo(n: Int): Boolean = n % 2 == 0

// 5. Vrne večjega od dveh števil
fun vecje(a: Int, b: Int): Int = if (a > b) a else b

// 6. Vrne največje izmed treh
fun najvecje(a: Int, b: Int, c: Int): Int = maxOf(a, b, c)

// 7. Naključno število med 1 in 100 (vključno)
fun nakljucno(): Int = Random.nextInt(1, 101)

// 8. Združitev dveh nizov s presledkom
fun zdruzi(a: String, b: String): String = "$a $b"

// 9. Funkcija z neobveznim parametrom 'ura' (privzeta vrednost 12)
fun pozdrav(ime: String, ura: Int = 12) {
    val pozdrav = when {
        ura < 11 -> "Dobro jutro"
        ura < 18 -> "Dober dan"
        else -> "Dober večer"
    }
    println("9. $pozdrav, $ime! (ura $ura)")
}

main()