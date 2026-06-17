package projekti

/**
 * Projekt 1: Nakupovalni seznam
 *
 * - Podatkovni razred Artikel(naziv, vKosarici)
 * - Wrapper razred NakupovalniSeznam z metodami za dodajanje, brisanje,
 *   spreminjanje naziva, preklop "v košarici", iskanje in izpis.
 * - CLI vmesnik z meniji.
 */

data class Artikel(
    var naziv: String,
    var vKosarici: Boolean = false
)

class NakupovalniSeznam {
    private val artikli = mutableListOf<Artikel>()

    fun dodaj(naziv: String) {
        artikli.add(Artikel(naziv))
    }

    fun odstrani(index: Int): Boolean {
        if (index !in artikli.indices) return false
        artikli.removeAt(index)
        return true
    }

    fun spremeniNaziv(index: Int, novNaziv: String): Boolean {
        if (index !in artikli.indices) return false
        artikli[index].naziv = novNaziv
        return true
    }

    fun preklopiKosarico(index: Int): Boolean {
        if (index !in artikli.indices) return false
        artikli[index].vKosarici = !artikli[index].vKosarici
        return true
    }

    /** Vrne true, če je artikel z danim nazivom na seznamu (neobčutljivo na velikost črk). */
    fun vsebuje(naziv: String): Boolean =
        artikli.any { it.naziv.equals(naziv, ignoreCase = true) }

    fun izpisi() {
        if (artikli.isEmpty()) {
            println("(seznam je prazen)")
            return
        }
        artikli.forEachIndexed { i, a ->
            val marker = if (a.vKosarici) "[x]" else "[ ]"
            println("  ${i + 1}. $marker ${a.naziv}")
        }
    }

    fun velikost(): Int = artikli.size
}

fun main() {
    val seznam = NakupovalniSeznam()
    // začetni primeri
    seznam.dodaj("mleko")
    seznam.dodaj("moka")
    seznam.dodaj("jajca")

    while (true) {
        println("\n=== Nakupovalni seznam (${seznam.velikost()} art.) ===")
        seznam.izpisi()
        println(
            """
            |
            |1) Dodaj artikel
            |2) Odstrani artikel
            |3) Spremeni naziv
            |4) Preklopi "v košarici"
            |5) Preveri ali vsebuje
            |0) Izhod
            """.trimMargin()
        )
        print("Izbira: ")
        when (readLine()?.trim()) {
            "1" -> {
                print("Naziv: ")
                val n = readLine()?.trim().orEmpty()
                if (n.isNotEmpty()) {
                    seznam.dodaj(n)
                    println("Dodano.")
                } else println("Prazen naziv - preskočeno.")
            }
            "2" -> {
                print("Številka artikla: ")
                val i = readLine()?.toIntOrNull()
                println(
                    if (i != null && seznam.odstrani(i - 1)) "Odstranjeno."
                    else "Neveljavna številka."
                )
            }
            "3" -> {
                print("Številka artikla: ")
                val i = readLine()?.toIntOrNull()
                print("Nov naziv: ")
                val n = readLine()?.trim().orEmpty()
                println(
                    if (i != null && n.isNotEmpty() && seznam.spremeniNaziv(i - 1, n)) "Spremenjeno."
                    else "Neveljavni podatki."
                )
            }
            "4" -> {
                print("Številka artikla: ")
                val i = readLine()?.toIntOrNull()
                println(
                    if (i != null && seznam.preklopiKosarico(i - 1)) "Preklopljeno."
                    else "Neveljavna številka."
                )
            }
            "5" -> {
                print("Naziv: ")
                val n = readLine()?.trim().orEmpty()
                println(if (seznam.vsebuje(n)) "Je na seznamu." else "Ni na seznamu.")
            }
            "0" -> {
                println("Zbogom!")
                return
            }
            else -> println("Neveljavna izbira.")
        }
    }
}

main()