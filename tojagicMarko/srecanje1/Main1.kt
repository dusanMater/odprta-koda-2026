import kotlin.random.Random

data class Artikel(val naziv: String, val vKosarici: Boolean = false)

class NakupovalniSeznam {
    private val seznam = mutableListOf<Artikel>()

    fun dodaj(naziv: String) {
        seznam.add(Artikel(naziv))
        println("Dodano: $naziv")
    }

    fun odstrani(naziv: String) {
        val odstranjen = seznam.removeIf { it.naziv.equals(naziv, ignoreCase = true) }
        if (odstranjen) println("Odstranjeno: $naziv")
        else println("Artikel ne obstaja")
    }

    fun spremeni(stari: String, novi: String) {
        val index = seznam.indexOfFirst { it.naziv.equals(stari, ignoreCase = true) }
        if (index != -1) {
            seznam[index] = Artikel(novi, seznam[index].vKosarici)
            println("Spremenjeno: $stari -> $novi")
        } else println("Artikel ne obstaja")
    }

    fun vKosarico(naziv: String) {
        val index = seznam.indexOfFirst { it.naziv.equals(naziv, ignoreCase = true) }
        if (index != -1) {
            seznam[index] = Artikel(seznam[index].naziv, true)
            println("$naziv je zdaj v košarici")
        } else println("Artikel ne obstaja")
    }

    fun izpisi() {
        if (seznam.isEmpty()) println("Seznam je prazen")
        else {
            println("\nNakupovalni seznam")
            for ((i, artikel) in seznam.withIndex()) {
                val ikona = if (artikel.vKosarici) "[x]" else "[ ]"
                println("${i+1}. $ikona ${artikel.naziv}")
            }
        }
    }

    fun vsebuje(naziv: String): Boolean {
        return seznam.any { it.naziv.equals(naziv, ignoreCase = true) }
    }
}

fun main() {
    val ns = NakupovalniSeznam()
    println("Ukazi: dodaj, odstrani, spremeni, kosarica, izpisi, vsebuje, konec")

    while (true) {
        print("> ")
        when (readlnOrNull()?.trim()?.lowercase()) {
            "dodaj" -> {
                print("Naziv: ")
                val n = readlnOrNull()?.trim() ?: continue
                ns.dodaj(n)
            }
            "odstrani" -> {
                print("Naziv: ")
                val n = readlnOrNull()?.trim() ?: continue
                ns.odstrani(n)
            }
            "spremeni" -> {
                print("Stari naziv: ")
                val s = readlnOrNull()?.trim() ?: continue
                print("Novi naziv: ")
                val n = readlnOrNull()?.trim() ?: continue
                ns.spremeni(s, n)
            }
            "kosarica" -> {
                print("Kateri artikel: ")
                val n = readlnOrNull()?.trim() ?: continue
                ns.vKosarico(n)
            }
            "izpisi" -> ns.izpisi()
            "vsebuje" -> {
                print("Preveri: ")
                val n = readlnOrNull()?.trim() ?: continue
                println(if (ns.vsebuje(n)) "Artikel obstaja" else "Ne obstaja")
            }
            "konec" -> break
            else -> println("Neveljaven ukaz")
        }
    }
}