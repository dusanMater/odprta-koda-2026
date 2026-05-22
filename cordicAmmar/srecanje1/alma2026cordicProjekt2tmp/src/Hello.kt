fun main() {
    val test = Artikel("Test")
    println(test.ime)

    while (true) {
        menu()
        var izbira: String = readln()
        var st: Int = izbira.toInt()

        when (st) {
            1 -> {
                println("Vstavljanje artikla v seznam")
            }
            2 -> {
                println("Izpis seznama (artiklov)")
            }
            3 -> {
                println("Oznacevanje, ali je ze v kosarici artikel")
            }
            4 -> {
                println("Brisanje iz seznama")
            }
            5 -> {
                println("Konec")
                break
            }
            else -> {
                println("Neveljavna izbira")
            }
        }
    }


}

fun menu() {
    println("\n1 - vstavljanje artikla v seznam")
    println("2 - izpis seznama (artiklov)")
    println("3 - oznacevanje, ali je ze v kosarici artikel")
    println("4 - brisanje iz seznama")
    println("5 - konec")
    print("Izbira:")
}