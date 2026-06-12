data class Artikel(var naziv: String, var vKosarici: Boolean)

fun main() {
    val seznam = mutableListOf<Artikel>()
    while (true) {
        println("\n--- MENI ---")
        println("1 - Dodajanje artikla")
        println("2 - Brisanje artikla")
        println("3 - Spremeni naziv artikla")
        println("4 - Označi kot v košarici")
        println("5 - Izpiši seznam")
        println("6 - Preveri če obstaja")
        println("0 - Izhod")
        val izbira = readln()
        when (izbira) {
            "1" -> {
                println("Vpiši naziv artikla:")
                val naziv = readln()
                val artikel = Artikel(naziv, false)
                seznam.add(artikel)
                println("Dodan!")
            }
            "2" -> {
                println("Vpiši naziv artikla za brisanje:")
                val naziv = readln()
                val najden = seznam.find { it.naziv == naziv }

                if (najden != null) {
                    seznam.remove(najden)
                    println("Izbrisano!")
                } else {
                    println("Ni najdeno.")
                }
            }
            "3" -> {
                println("Stari naziv:")
                val star = readln()
                val artikel = seznam.find { it.naziv == star }
                if (artikel != null) {
                    println("Novi naziv:")
                    val nov = readln()
                    artikel.naziv = nov
                    println("Spremenjeno!")
                } else {
                    println("Ni najdeno.")
                }
            }
            "4" -> {
                println("Vpiši naziv:")
                val naziv = readln()
                val artikel = seznam.find { it.naziv == naziv }
                if (artikel != null) {
                    artikel.vKosarici = true
                    println("Označeno kot v košarici.")
                } else {
                    println("Ni najdeno.")
                }
            }
            "5" -> {
                println("\n- SEZNAM -")
                for (a in seznam) {
                    println("${a.naziv} | v košarici: ${a.vKosarici}")
                }
            }
            "6" -> {
                println("Vpiši naziv:")
                val naziv = readln()
                val obstaja = seznam.any { it.naziv == naziv }
                println(obstaja)
            }
            "0" -> {
                println("Konec programa.")
                break
            }
            else -> println("Napačna izbira")
        }
    }
}
