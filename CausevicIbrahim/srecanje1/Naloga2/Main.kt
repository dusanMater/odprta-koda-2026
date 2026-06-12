import kotlin.random.Random

data class PasswordRule(
    val dolzina: Int,
    val stevilke: Boolean,
    val simboli: Boolean
)

fun main() {

    println("Vpiši dolžino gesla:")
    val vnosDolzina = readln().toInt()

    println("Uporabi številke? (da/ne)")
    val vnosStevilke = readln()
    val imaStevilke = vnosStevilke.lowercase() == "da"

    println("Uporabi posebne znake? (da/ne)")
    val vnosSimboli = readln()
    val imaSimboli = vnosSimboli.lowercase() == "da"

    val pravila = PasswordRule(vnosDolzina, imaStevilke, imaSimboli)

    val geslo = ustvariGeslo(pravila)

    println("Generirano geslo: $geslo")
}

fun ustvariGeslo(pravila: PasswordRule): String {

    val maleCrke = "abcdefghijklmnopqrstuvwxyz"
    val velikeCrke = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val cifre = "0123456789"
    val posebniZnaki = "!@#\$%^&*()"

    var dovoljeni = maleCrke + velikeCrke

    if (pravila.stevilke) {
        dovoljeni += cifre
    }

    if (pravila.simboli) {
        dovoljeni += posebniZnaki
    }

    val rezultat = StringBuilder()

    for (i in 1..pravila.dolzina) {
        val znak = dovoljeni[Random.nextInt(dovoljeni.length)]
        rezultat.append(znak)
    }

    return rezultat.toString()
}