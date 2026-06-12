class NakupovalniSeznam {
    val artikli = mutableListOf<Artikel>()

    fun dodajArtikel(naziv: String) {
        artikli.add(Artikel(naziv))
        println("Artikel $naziv je dodan na seznam.")
    }

    fun izbrisiArtikel(naziv: String) {
        var indeks = -1
        for (i in artikli.indices) {
            if (artikli[i].naziv == naziv) {
                indeks = i
                break
            }
        }
        if (indeks != -1) {
            artikli.removeAt(indeks)
            println("Artikel $naziv je izbrisan.")
        } else {
            println("Artikel $naziv ni najden.")
        }
    }

    fun spremeniNaziv(stariNaziv: String, noviNaziv: String) {
        var najden = false
        for (artikel in artikli) {
            if (artikel.naziv == stariNaziv) {
                artikel.naziv = noviNaziv
                println("Naziv spremenjen iz $stariNaziv v $noviNaziv.")
                najden = true
                break
            }
        }
        if (!najden) {
            println("Artikel $stariNaziv ni najden.")
        }
    }

    fun nastaviVKosarici(naziv: String, vKosarici: Boolean) {
        var najden = false
        for (artikel in artikli) {
            if (artikel.naziv == naziv) {
                artikel.vKosarici = vKosarici
                println("Artikel $naziv - v kosarici: $vKosarici")
                najden = true
                break
            }
        }
        if (!najden) {
            println("Artikel $naziv ni najden.")
        }
    }

    fun vsebujeArtikel(naziv: String): Boolean {
        for (artikel in artikli) {
            if (artikel.naziv == naziv) {
                return true
            }
        }
        return false
    }

    fun izpisiSeznam() {
        if (artikli.isEmpty()) {
            println("Nakupovalni seznam je prazen.")
        } else {
            println("Nakupovalni seznam:")
            var i = 1
            for (artikel in artikli) {
                val stanje = if (artikel.vKosarici) "v kosarici" else "ni v kosarici"
                println("$i. ${artikel.naziv} - $stanje")
                i++
            }
        }
    }
}
