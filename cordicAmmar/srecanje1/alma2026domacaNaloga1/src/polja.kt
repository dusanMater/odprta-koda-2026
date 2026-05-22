fun polja() {
    // 1
    val seznam = listOf(1, 2, 3, 4, 5)
    println("List celih števil: $seznam")

    // 2
    val mutable = mutableListOf(1, 2, 3)
    mutable.add(4)
    println("Dodana 4 v mutable list (1,2,3): $mutable")

    // 3
    mutable.remove(2)
    println("Odstranjena 2 iz mutable lista: $mutable")

    // 4
    println("Največje število v seznamu je: " + seznam.max())

    // 5
    println("ForEach za izpis vseh elementov")
    seznam.forEach {
        print("$it, ")
    }

    // 6
    val soda = seznam.filter { it % 2 == 0 }
    println("Soda števila v seznamu: $soda")

    // 7
    val nesortirano = mutableListOf(5, 1, 9, 2)
    println("Nesortiran seznam: $nesortirano")
    nesortirano.sort()
    println("Sortiran seznam: $nesortirano")

    // 8
    println("Ali seznam vsebuje element 3? " + seznam.contains(3))

    // 9
    val besede = listOf("Java", "Kotlin", "Python")
    println("Seznam besed Java, Kotlin in Python v enem stringu: " + besede.joinToString(", "))
}