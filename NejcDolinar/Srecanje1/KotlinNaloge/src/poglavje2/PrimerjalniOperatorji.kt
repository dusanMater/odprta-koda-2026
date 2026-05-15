package poglavje2

/**
 * 2.2 Primerjalni operatorji (==, !=, <, >, <=, >=)
 */
fun main() {
    println("=== 2.2 Primerjalni operatorji ===\n")

    // 1. Primerjava dveh števil - katera je večja?
    val a = 17
    val b = 23
    val odgovor = when {
        a > b -> "$a je večje"
        b > a -> "$b je večje"
        else -> "$a in $b sta enaki"
    }
    println("1. $odgovor")

    // 2. Ali je x == y?
    val x = 5
    val y = 5
    println("2. x == y -> ${x == y}")

    // 3. starost >= 18 -> "polnoleten"
    val starost = 19
    if (starost >= 18) println("3. polnoleten") else println("3. mladoleten")

    // 4. Primerjava dolžin dveh nizov
    val niz1 = "Pozdravljen"
    val niz2 = "Svet"
    println("4. Dolžini: ${niz1.length} vs ${niz2.length} -> " +
            if (niz1.length > niz2.length) "prvi je daljši"
            else if (niz1.length < niz2.length) "drugi je daljši"
            else "enako dolga")

    // 5. Različen od nič
    val vrednost = 42
    println("5. $vrednost != 0 -> ${vrednost != 0}")

    // 6. Sta niza enaka?
    val s1 = "Kotlin"
    val s2 = "Kotlin"
    println("6. '$s1' == '$s2' -> ${s1 == s2}")

    // 7. a < b && b < c
    val aa = 3
    val bb = 7
    val cc = 12
    println("7. ($aa < $bb && $bb < $cc) -> ${aa < bb && bb < cc}")

    // 8. Če je score med 90 in 100 -> "Odlično"
    val score = 95
    if (score in 90..100) println("8. Odlično") else println("8. Ni odlično")

    // 9. Primerjava dveh znakov
    val zn1 = 'a'
    val zn2 = 'z'
    println("9. '$zn1' < '$zn2' -> ${zn1 < zn2}")

    // 10. Primerjava datumov (kot String - znakovno; format YYYY-MM-DD)
    val datum1 = "2025-11-14"
    val datum2 = "2025-12-01"
    println("10. '$datum1' < '$datum2' -> ${datum1 < datum2}  (lex. primerjava)")
    println("    dolžini: ${datum1.length} vs ${datum2.length}")
}
