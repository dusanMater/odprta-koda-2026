fun main() {
    println("--- Generator gesel ---")

    while (true) {
        // Vnos dolžine
        print("Vnesi dolžino gesla: ")
        val dolzina = readLine()?.toIntOrNull() ?: 0

        if (dolzina <= 0) {
            println("Dolžina mora biti pozitivno število.")
            continue
        }

        // Vnos, ali naj geslo vsebuje številke
        print("Naj geslo vsebuje številke? (d/n): ")
        val stevilke = readLine()?.trim().equals("d", ignoreCase = true)

        // Vnos, ali naj geslo vsebuje posebne znake
        print("Naj geslo vsebuje posebne znake? (d/n): ")
        val simboli = readLine()?.trim().equals("d", ignoreCase = true)

        // Sestavi pravilo in generiraj geslo
        val pravilo = PasswordRule(
            length = dolzina,
            useNumbers = stevilke,
            useSymbols = simboli
        )
        val geslo = Generator.ustvari(pravilo)

        println("Generirano geslo: $geslo")

        // Vprašaj, ali naj generira še eno
        print("\nGeneriraj še eno geslo? (d/n): ")
        val odgovor = readLine()?.trim()
        if (!odgovor.equals("d", ignoreCase = true)) {
            println("Konec")
            break
        }
    }
}
