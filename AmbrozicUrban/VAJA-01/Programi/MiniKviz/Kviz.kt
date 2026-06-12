class Kviz {
    // Nabor vprašanj — prve tri so iz primera v navodilih, ostale so dodatek
    private val vprasanja = listOf(
        Question("Glavno mesto Slovenije?", "Ljubljana"),
        Question("Koliko je 5 + 7?", "12"),
        Question("Kateri jezik se uporablja za Android?", "Kotlin"),
        Question("Najvišji vrh v Sloveniji?", "Triglav"),
        Question("Koliko je 8 * 7?", "56"),
        Question("Glavno mesto Avstrije?", "Dunaj"),
        Question("Najdaljša reka v Sloveniji?", "Sava"),
        Question("V katerem letu se je Slovenija osamosvojila?", "1991")
    )

    fun velikostNabora(): Int = vprasanja.size

    // Vrne n naključno izbranih vprašanj brez ponavljanja
    fun izberi(n: Int): List<Question> {
        return vprasanja.shuffled().take(n)
    }

    // Preveri, ali se odgovor uporabnika ujema s pravilnim odgovorom (neobčutljivo na velikost črk)
    fun preveri(vprasanje: Question, odgovorUporabnika: String): Boolean {
        return odgovorUporabnika.trim().lowercase() == vprasanje.answer.lowercase()
    }
}
