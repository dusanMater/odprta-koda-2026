import kotlin.random.Random

data class PasswordRule(val length: Int, val useNumbers: Boolean, val useSymbols: Boolean)

fun main() {
    print("Vnesi dolžino gesla: ")
    val dolzina = readlnOrNull()?.toIntOrNull() ?: return
    if (dolzina < 4) {
        println("Geslo naj ima vsaj 4 znake")
        return
    }

    print("Vključi številke? (d/n): ")
    val stevilke = readlnOrNull()?.lowercase() == "d"
    print("Vključi posebne znake? (d/n): ")
    val simboli = readlnOrNull()?.lowercase() == "d"

    val pravilo = PasswordRule(dolzina, stevilke, simboli)

    var nabor = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    if (pravilo.useNumbers) nabor += "0123456789"
    if (pravilo.useSymbols) nabor += "!@#$%&*?-_+"

    val geslo = StringBuilder()
    repeat(pravilo.length) {
        val randomIndex = Random.nextInt(nabor.length)
        geslo.append(nabor[randomIndex])
    }

    println("Generirano geslo: $geslo")
}