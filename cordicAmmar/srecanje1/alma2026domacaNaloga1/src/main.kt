fun main() {
    println("1. Delo s spremenljivkami")
    println("-".repeat(30))
    deloSSpremenljivkami()

    println("=".repeat(30))

    println("\n\n2. Operatorji")
    println("-".repeat(30))
    operatorji()

    println("=".repeat(30))

    println("\n\nPogojni stavki in zanke")
    println("-".repeat(30))
    pogojniStavkiInZanke()

    println()
    println("=".repeat(30))

    println("\n\nFunkcije")
    println("-".repeat(30))
    funkcije()

    println("=".repeat(30))

    println("\n\nRazredi in podatkovni razredi")
    println("-".repeat(30))
    razrediInPodatkovniRazredi()

    println("=".repeat(30))

    println("\n\nPolja")
    println("-".repeat(30))
    polja()

    println("=".repeat(30))




}







// =========================
// PROJEKT 1 - NAKUPOVALNI SEZNAM
// =========================

/*

data class Artikel(
    var naziv: String,
    var vKosarici: Boolean
)

class NakupovalniSeznam {

    private val artikli = mutableListOf<Artikel>()

    fun dodajArtikel(artikel: Artikel) {
        artikli.add(artikel)
    }

    fun odstraniArtikel(naziv: String) {
        artikli.removeIf { it.naziv == naziv }
    }

    fun spremeniNaziv(stariNaziv: String, noviNaziv: String) {
        artikli.find { it.naziv == stariNaziv }?.naziv = noviNaziv
    }

    fun oznaciVKosarici(naziv: String) {
        artikli.find { it.naziv == naziv }?.vKosarici = true
    }

    fun izpisi() {
        artikli.forEach {
            println(it)
        }
    }
}

fun main() {

    val seznam = NakupovalniSeznam()

    seznam.dodajArtikel(Artikel("Mleko", false))
    seznam.dodajArtikel(Artikel("Kruh", false))

    seznam.oznaciVKosarici("Mleko")

    seznam.spremeniNaziv("Kruh", "Polnozrnat kruh")

    seznam.izpisi()
}

*/


// =========================
// PROJEKT 2 - GENERATOR GESEL
// =========================

/*

data class PasswordRule(
    val length: Int,
    val useNumbers: Boolean,
    val useSymbols: Boolean
)

fun generatePassword(rule: PasswordRule): String {

    val lowercase = "abcdefghijklmnopqrstuvwxyz"
    val uppercase = lowercase.uppercase()
    val numbers = "0123456789"
    val symbols = "!@#$%^&*"

    var allowed = lowercase + uppercase

    if (rule.useNumbers) {
        allowed += numbers
    }

    if (rule.useSymbols) {
        allowed += symbols
    }

    val password = StringBuilder()

    repeat(rule.length) {
        val randomChar = allowed.random()
        password.append(randomChar)
    }

    return password.toString()
}

fun main() {

    val rule = PasswordRule(12, true, true)

    val password = generatePassword(rule)

    println("Geslo: $password")
}

*/


// =========================
// PROJEKT 3 - MINI KVIZ
// =========================

/*

data class Question(
    val text: String,
    val answer: String
)

fun main() {

    val questions = listOf(
        Question("Glavno mesto Slovenije?", "Ljubljana"),
        Question("Koliko je 5 + 7?", "12"),
        Question("Kateri jezik se uporablja za Android?", "Kotlin")
    )

    val question = questions.random()

    println(question.text)

    val userAnswer = readln()

    if (userAnswer.lowercase() == question.answer.lowercase()) {
        println("Pravilno!")
    } else {
        println("Napačno!")
    }
}

*/