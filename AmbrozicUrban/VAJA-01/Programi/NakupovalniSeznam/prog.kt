fun main() {
    val seznam = Seznam()

    while (true) {
        menu()

        val izbira = readLine() ?: ""
        val st = izbira.toIntOrNull() ?: 0

        if (st == 1) {
            // Dodajanje na seznam
            print("Vnesi ime artikla: ")
            val ime = readLine() ?: ""

            if (ime.isNotBlank()) {
                seznam.dodaj(ime)
                println("Artikel '$ime' dodan.")
            }
            else {
                println("Ime ne sme biti prazno.")
            }
        }

        else if (st == 2) {
            // Izpis seznama
            seznam.izpis()
        }

        else if (st == 3) {
            // Preklop stanja (v košarici / še ni v košarici)
            if (seznam.jePrazen()) {
                println("Seznam je prazen.")
            }
            else {
                seznam.izpis()
                print("Vnesi številko artikla za označevanje: ")
                val stevilka = readLine()?.toIntOrNull() ?: 0
                if (seznam.preklopiStanje(stevilka)) {
                    println("Stanje spremenjeno.")
                }
                else {
                    println("Napačna številka.")
                }
            }
        }

        else if (st == 4) {
            // Preimenovanje artikla
            if (seznam.jePrazen()) {
                println("Seznam je prazen.")
            }
            else {
                seznam.izpis()
                print("Vnesi številko artikla za preimenovanje: ")
                val stevilka = readLine()?.toIntOrNull() ?: 0
                print("Vnesi novo ime: ")
                val novoIme = readLine() ?: ""

                if (novoIme.isBlank()) {
                    println("Ime ne sme biti prazno.")
                }
                else if (seznam.preimenuj(stevilka, novoIme)) {
                    println("Artikel preimenovan v '$novoIme'.")
                }
                else {
                    println("Napačna številka.")
                }
            }
        }

        else if (st == 5) {
            // Preverjanje, če je artikel na seznamu
            print("Vnesi ime artikla za iskanje: ")
            val ime = readLine() ?: ""

            if (ime.isBlank()) {
                println("Ime ne sme biti prazno.")
            }
            else {
                val najden = seznam.najdi(ime)
                if (najden == null) {
                    println("Artikel '$ime' ni na seznamu.")
                }
                else if (najden.stanje) {
                    println("Da, '${najden.ime}' je na seznamu (v košarici).")
                }
                else {
                    println("Da, '${najden.ime}' je na seznamu (še ni v košarici).")
                }
            }
        }

        else if (st == 6) {
            // Brisanje iz seznama
            if (seznam.jePrazen()) {
                println("Seznam je prazen.")
            }
            else {
                seznam.izpis()
                print("Vnesi številko artikla za brisanje: ")
                val stevilka = readLine()?.toIntOrNull() ?: 0
                val odstranjen = seznam.odstrani(stevilka)
                if (odstranjen != null) {
                    println("Artikel '${odstranjen.ime}' odstranjen.")
                }
                else {
                    println("Napačna številka.")
                }
            }
        }

        else if (st == 7) {
            println("Konec")
            break
        }
        else {
            println("Napačna izbira")
        }
    }
}


/**
 * Funkcija na ekran izpiše "menu"
 */
fun menu() {
    println("\n1 - Vstavljanje artikla v seznam")
    println("2 - Izpis seznama")
    println("3 - Označevanje (preklop stanja)")
    println("4 - Preimenovanje artikla")
    println("5 - Preverjanje, če je artikel na seznamu")
    println("6 - Brisanje iz seznama")
    println("7 - Konec")
    print("Izbira: ")
}
