package luolastogeneraattori.algo

import luolastogeneraattori.util.*

class RanPrim {
    private var vastakkainen = mapOf("oikea" to "vasen", "vasen" to "oikea", "ylos" to "alas", "alas" to "ylos")
    private var reuna = arrayOf<Pair<Int, Int>>()

    /**
     * Merkitään annettu ruutu osaksi reunaa
     */
    private fun lisaaReunaan(y: Int, x: Int, ruudukko: Array<Array<Ruutu>>) {
        if (x >= 0 && y >= 0 && y < ruudukko.size && x < ruudukko[y].size && ruudukko[y][x].arvo == 0) {
            reuna += Pair(y, x)
        }
    }

    /**
     * Merkitään annettu ruutu osaksi ruudukkoa ja käsitellään sen naapurit
     */
    private fun merkitse(y: Int, x: Int, ruudukko: Array<Array<Ruutu>>) {
        // Ruutu osaksi labyrinttia.
        ruudukko[y][x].arvo = 1
        lisaaReunaan(y, x - 1, ruudukko)
        lisaaReunaan(y, x + 1, ruudukko)
        lisaaReunaan(y - 1, x, ruudukko)
        lisaaReunaan(y + 1, x, ruudukko)

        println("Ruudukko nyt: ")
        debugRuudukko(ruudukko)
    }

    /**
     * Lisää annetulle ruudulle naapurit sille osoitettuun taulukkoon
     */
    private fun naapurit(y: Int, x: Int, ruudukko: Array<Array<Ruutu>>) {
        if (x > 0 && ruudukko[y][x - 1].arvo != 0)                    ruudukko[y][x].naapurit += Triple(y, x-1, ruudukko[y][x-1])
        if (x + 1 < ruudukko[y].size && ruudukko[y][x + 1].arvo != 0) ruudukko[y][x].naapurit += Triple(y, x+1, ruudukko[y][x+1])
        if (y > 0 && ruudukko[y - 1][x].arvo != 0)                    ruudukko[y][x].naapurit += Triple(y-1, x, ruudukko[y-1][x])
        if (y + 1 < ruudukko.size && ruudukko[y + 1][x].arvo != 0)    ruudukko[y][x].naapurit += Triple(y+1, x, ruudukko[y+1][x])
    }

    /**
     * Suunnan laskeminen
     */
    private fun suunta(fy: Int, fx: Int, ty: Int, tx: Int): String {
        return when (true) {
            (fx < tx) -> "oikea"
            (fx > tx) -> "vasen"
            (fy < ty) -> "alas"
            (fy > ty) -> "ylos"
            else -> ""
        }
    }

    /**
     * Primin algoritmi
     */
    fun muunnaLabyrintiksi(ruudukko: Array<Array<Ruutu>>): Array<Array<Ruutu>> {
        // Lasketaan satunnaisesti koordinaatit lähtöruudulle
        val satunnaisX = ruudukko.indices.random()
        val satunnaisY = ruudukko[0].indices.random()

        // Alustetaan reunaruudukko ja vähennetään lukumäärästä ensimmäinen reunaruutu
        var counter = ruudukko.size * ruudukko[0].size - 1

        // Merkitään ruudukkoon aloitusruutu ja lisätään sen naapurit osaksi reunaa
        merkitse(satunnaisY, satunnaisX, ruudukko)
        println("Aloitusruutu: $satunnaisY, $satunnaisX")

        // Luupataan niin kauan, kun reunataulukossa on ruutuja
        do {
            // Valitaan ja poistetaan sattumalla piste reunalta
            val reunaPiste = reuna.random()
            reuna = reuna.filter { it != reunaPiste }.toTypedArray()
            counter--

            // Rakennetaan naapuritaulukko valitulle reunaruudulle
            naapurit(reunaPiste.first, reunaPiste.second, ruudukko)

            // Valitaan naapureista sattumalla ruutu
            println("Reunan koko: ${reuna.size}")

            val naapuri = ruudukko[reunaPiste.first][reunaPiste.second].naapurit.random()

            println("Valittiin sattumalta naapuri: ${naapuri.first}, ${naapuri.second}")

            // Lasketaan suunta reunaruudusta naapuriruutuun
            val suunt = suunta(reunaPiste.first, reunaPiste.second, naapuri.first, naapuri.second)

            // Merkitään valitun reunaruudun suunnaksi laskettu suunta
            ruudukko[reunaPiste.first][reunaPiste.second].suunta = suunt

            // Merkitään naapuriruudun suunnaksi vastakkaissuunta suhteessa reunaruutuun
            ruudukko[naapuri.first][naapuri.second].suunta = vastakkainen[suunt]

            // Merkitään reunaruutu osaksi ruudukkoa
            merkitse(reunaPiste.first, reunaPiste.second, ruudukko)
        } while (counter > 0)

        return ruudukko
    }
}
