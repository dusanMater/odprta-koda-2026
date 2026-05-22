fun main()
{
    //print(1.0/2)
    //val test = Artikel("Mleko") // nov objekt ki ima lastnost ime nastavljeno na Mleko, stankje pa na false
    //println ("ime je " + test.ime)
    //println( "stanje je " + test.stanje)

    val seznam = mutableListOf<Artikel>(Artikel("Mleko"),Artikel("Kruh"),Artikel("Puter")) // ustvari prazen spremelnljivk seznam Artiklov

    while(true) {
        try {
            menu() // klic funkcije
            var izbira: String = readln()  // ustavi program in omogoči hranjenje vpisane vrednsti
            var st: Int = izbira.toInt()
            // če vpišemo število 2 se bo izpisal seznam
            // preveriti, kaj smo vpisali
            // težava, vpisanega števila nismo shranili
            // za to uporabimo spremenljivko

            if (st == 1) {
                println("vpis v seznama")// izpišemo kaj delamo
                print("Vpiši ime artikla:") // izpišemo kaj naj uporabnik vpiše
                izbira= readln()    // preberemo in shranimo kaj je uporabnik vpisal

                // pred vpisamo preveri, če slučajno v seznamu že ni vpisan isti artikel!!!
                var present: Boolean = false
                for (x in seznam) {
                    if(izbira.lowercase() == x.ime.lowercase()) {
                        println("Izdelek že v seznamu")
                        present = true
                        break
                    }
                }

                if (!present)
                    seznam.add(Artikel(izbira)) // v seznam dodamo nov artikel, čigar ime je uporabnik vpisal


            } else if (st == 2) {
                println("izpis iz seznama")
                // preveri, koliko je elementov v seznemu in vsakega posebej izpiši iz seznama
                // 3
                // zanko, ki se trikrta izvede ali pa kar for zanko ki gre čez seznama
                // izpišem za vsaki element obe lasntosti

                var st = seznam.count()    // 3
                var i = 0
                println("=".repeat(30))
                while(i < st )
                {
                    println(seznam.get(i).ime + " => " + seznam.get(i).stanje)
                    i++
                }
                println("=".repeat(30))
                for(x in seznam)
                {
                    println(x.ime + " => " + x.stanje)
                }
                println("=".repeat(30))
            } else if (st == 3) {
                print("označevanje")
            } else if (st == 4) {
                print("brisanje")
            } else if (st == 5) {
                print("konec")
                break
            } else {
                print("nakapak!")
            }
        } catch (e: NumberFormatException) {
            println("Napaka vnosa, program pričakuje vnos številke")
        } catch (e: Exception) {
            println("Prišlo je do napake, poskusite znova")
            println("Izpis napake (DEBUG):")
            println(e.message)
            println(e.stackTrace)
        }
    }

}





/**
 *  Funkcija na ekran izpiše menu
 *
 */
fun menu()
{
    println("\n1 - vstavljanje artikla v seznam")
    println("2 - izpis seznama (artiklov)")
    println("3 - potrdi, da sem v košarico dal aritkel")
    println("4 - brisanje iz seznama")
    println("5 - konec")
    print("Izbira:")
}
