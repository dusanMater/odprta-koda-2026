package poglavje1

import kotlin.math.roundToInt

/**
 * 1. Delo s spremenljivkami
 * Zaženi: zelena puščica ob 'main' v IntelliJ.
 */
fun main() {
    println("=== 1. Delo s spremenljivkami ===\n")

    // 1. Spremenljivka 'ime' tipa String
    val ime: String = "Patrik"
    println("1. Ime: $ime")

    // 2. Celoštevilska spremenljivka 'letoRojstva' + izračun starosti
    val letoRojstva: Int = 2002
    val trenutnoLeto = 2026
    val starost: Int = trenutnoLeto - letoRojstva
    println("2. Leto rojstva: $letoRojstva, starost: $starost let")

    // 3. Pretvorba starosti v Double
    val starostDouble: Double = starost.toDouble()
    println("3. Starost kot Double: $starostDouble")

    // 4. 'val' = read-only. Naslednja vrstica se NE prevede:
    val nespremenljiva = 100
    println("4. val 'nespremenljiva' = $nespremenljiva (spremeniti je ni mogoče)")

    // 5. Razlika med val (konstanta) in var (spremenljiva)
    val konstanta = "ne morem se spremeniti"
    var spremenljivka = "lahko se spremenim"
    spremenljivka = "spremenila sem se"
    println("5. val -> $konstanta")
    println("   var -> $spremenljivka")

    // 6. 'cena' tipa Float, zaokroženje na celo število
    val cena: Float = 12.7f
    val zaokrozenaCena: Int = cena.roundToInt()
    println("6. Cena $cena € zaokrožena: $zaokrozenaCena €")

    // 7. Povprečje treh števil
    val a = 5
    val b = 10
    val c = 15
    val povprecje = (a + b + c) / 3.0
    println("7. Povprečje ($a, $b, $c) = $povprecje")

    // 8. Vpis imena, izpis v zahtevani obliki
    print("8. Vpiši ime: ")
    val vpisanoIme = readLine()?.takeIf { it.isNotBlank() } ?: "Tilen"
    println("   Tvoje ime je $vpisanoIme.")

    // 9. Dolžina niza s funkcijo length
    println("9. Dolžina niza '$vpisanoIme' je ${vpisanoIme.length}")

    // 10. Prvi znak iz niza
    val prviZnak = vpisanoIme[0]   // ali: vpisanoIme.first()
    println("10. Prvi znak imena: $prviZnak")
}

main()
