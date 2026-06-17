package poglavje5

/**
 * 5. Razredi in podatkovni razredi
 *
 * Razred 'Oseba' z imenom in starostjo + metoda predstaviSe() in toString().
 */
class Oseba(val ime: String, val starost: Int) {

    // 2. Metoda predstaviSe()
    fun predstaviSe() {
        println("Pozdravljeni, sem $ime in imam $starost let.")
    }

    // 4. Lasten toString() - če bi šlo za 'data class', bi ga Kotlin
    //    samodejno generiral, tu pa ga pišemo ročno za 'class'.
    override fun toString(): String = "Oseba(ime='$ime', starost=$starost)"
}

fun main() {
    println("=== 5. Razredi ===\n")

    // 3. Tri osebe + izpis podatkov
    val o1 = Oseba("Patrik", 24)
    val o2 = Oseba("Ana", 20)
    val o3 = Oseba("Leo", 28)

    println("predstaviSe():")
    o1.predstaviSe()
    o2.predstaviSe()
    o3.predstaviSe()

    println("\n4. toString() izpisi:")
    println(o1)
    println(o2)
    println(o3)
}

main()