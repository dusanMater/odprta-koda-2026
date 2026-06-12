class Seznam {
    private val artikli = mutableListOf<Artikel>()

    fun jePrazen(): Boolean = artikli.isEmpty()

    fun velikost(): Int = artikli.size

    fun dodaj(ime: String) {
        artikli.add(Artikel(ime = ime))
    }

    fun odstrani(stevilka: Int): Artikel? {
        if (stevilka !in 1..artikli.size) return null
        return artikli.removeAt(stevilka - 1)
    }

    fun preimenuj(stevilka: Int, novoIme: String): Boolean {
        if (stevilka !in 1..artikli.size) return false
        artikli[stevilka - 1].ime = novoIme
        return true
    }

    fun preklopiStanje(stevilka: Int): Boolean {
        if (stevilka !in 1..artikli.size) return false
        artikli[stevilka - 1].stanje = !artikli[stevilka - 1].stanje
        return true
    }

    // Vrne artikel z ujemajočim imenom (neobčutljivo na velikost črk), ali null če ga ni
    fun najdi(ime: String): Artikel? {
        return artikli.find { it.ime.equals(ime, ignoreCase = true) }
    }

    fun izpis() {
        if (artikli.isEmpty()) {
            println("Seznam je prazen.")
            return
        }
        println("--- Seznam ---")
        for (i in artikli.indices) {
            val oznaka = if (artikli[i].stanje) "[X]" else "[ ]"
            println("${i + 1}. $oznaka ${artikli[i].ime}")
        }
    }
}
