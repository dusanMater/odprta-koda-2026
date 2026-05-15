package projekti

import kotlin.random.Random

/**
 * Projekt 2: Generator gesel
 *
 * Uporabnik vnese dolžino in opcije. Geslo vsebuje male/velike črke,
 * po želji še številke in posebne znake. Uporablja Random in StringBuilder.
 */

data class PasswordRule(
    val length: Int,
    val useNumbers: Boolean,
    val useSymbols: Boolean
)

fun generirajGeslo(rule: PasswordRule): String {
    require(rule.length > 0) { "Dolžina mora biti pozitivna." }

    val male = "abcdefghijklmnopqrstuvwxyz"
    val velike = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val stevilke = "0123456789"
    val simboli = "!@#$%^&*()-_=+[]{};:,.<>?/"

    // Sestavimo "pool" znakov glede na pravila
    val pool = buildString {
        append(male).append(velike)
        if (rule.useNumbers) append(stevilke)
        if (rule.useSymbols) append(simboli)
    }

    // Sestavimo geslo z StringBuilder-jem (učinkoviteje kot s + nizov v zanki)
    val sb = StringBuilder(rule.length)
    repeat(rule.length) {
        sb.append(pool[Random.nextInt(pool.length)])
    }
    return sb.toString()
}

private fun preberiDolzino(privzeto: Int = 12): Int {
    print("Vpiši dolžino gesla (privzeto $privzeto): ")
    val vnos = readLine()?.toIntOrNull()
    return if (vnos != null && vnos > 0) vnos else {
        println("(uporabljam privzeto $privzeto)")
        privzeto
    }
}

private fun preberiBoolean(vprasanje: String, privzeto: Boolean = true): Boolean {
    val privzetiZnak = if (privzeto) "d" else "n"
    print("$vprasanje (d/n, privzeto $privzetiZnak): ")
    val vnos = readLine()?.trim()?.lowercase().orEmpty()
    return when {
        vnos.startsWith("d") -> true
        vnos.startsWith("n") -> false
        else -> privzeto
    }
}

fun main() {
    println("=== Generator gesel ===\n")

    val dolzina = preberiDolzino()
    val skStevilkami = preberiBoolean("Vključim številke?")
    val sSimboli = preberiBoolean("Vključim posebne znake?")

    val rule = PasswordRule(
        length = dolzina,
        useNumbers = skStevilkami,
        useSymbols = sSimboli
    )

    println("\nGenerirano geslo: ${generirajGeslo(rule)}")
    println("Še eno za primerjavo: ${generirajGeslo(rule)}")
    println("In še tretje: ${generirajGeslo(rule)}")
}
