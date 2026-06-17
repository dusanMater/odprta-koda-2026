package poglavje6

/**
 * 6. Polja (seznami)
 */
fun main() {
    println("=== 6. Seznami ===\n")

    // 1. List celih števil (nespremenljiv)
    val stevila: List<Int> = listOf(5, 12, 3, 8, 20, 1, 14)
    println("1. Seznam: $stevila")

    // 2. Dodajanje v mutableListOf
    val sad = mutableListOf("jabolko", "ananas", "hruška")
    sad.add("breskva")
    println("2. Po dodajanju 'breskva': $sad")

    // 3. Odstranjevanje elementa
    sad.remove("banana")
    println("3. Po odstranjevanju 'banana': $sad")

    // 4. Največje število
    println("4. Največje v $stevila: ${stevila.max()}")

    // 5. forEach - izpis vseh elementov
    println("5. forEach izpis:")
    stevila.forEach { println("   $it") }

    // 6. Filtriranje - samo sode številke
    val soda = stevila.filter { it % 2 == 0 }
    println("6. Sode: $soda")

    // 7. Sortiranje naraščajoče
    val sortirano = stevila.sorted()
    println("7. Sortirano: $sortirano")

    // 8. contains()
    println("8. stevila.contains(8)  -> ${stevila.contains(8)}")
    println("   stevila.contains(99) -> ${stevila.contains(99)}")

    // 9. joinToString() - List<String> -> String z vejicami
    val imena = listOf("Jure", "Boris", "Janez")
    println("9. joinToString(\", \"): \"${imena.joinToString(", ")}\"")
}

main()