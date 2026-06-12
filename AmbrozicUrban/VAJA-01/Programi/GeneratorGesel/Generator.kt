import kotlin.random.Random

object Generator {
    private const val MALE_CRKE = "abcdefghijklmnopqrstuvwxyz"
    private const val VELIKE_CRKE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private const val STEVILKE = "0123456789"
    private const val SIMBOLI = "!@#\$%^&*()-_=+"

    fun ustvari(pravilo: PasswordRule): String {
        // Sestavi nabor znakov glede na pravilo
        val nabor = StringBuilder()
        nabor.append(MALE_CRKE)
        nabor.append(VELIKE_CRKE)
        if (pravilo.useNumbers) nabor.append(STEVILKE)
        if (pravilo.useSymbols) nabor.append(SIMBOLI)

        // Naključno izberi length znakov iz nabora
        val geslo = StringBuilder()
        for (i in 0 until pravilo.length) {
            val indeks = Random.nextInt(nabor.length)
            geslo.append(nabor[indeks])
        }
        return geslo.toString()
    }
}
