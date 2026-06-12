class Oseba (val ime: String, val starost: Int) {
    fun predstaviSe() {
        println("Sem $ime in star sem $starost let")
    }

    override fun toString(): String {
        return "Oseba(ime='$ime', starost=$starost)"
    }
}