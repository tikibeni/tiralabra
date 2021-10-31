package luolastogeneraattori.algo

import luolastogeneraattori.util.*

class RanPrim {
    private var vastakkainen = mapOf("oikea" to "vasen", "vasen" to "oikea", "ylos" to "alas", "alas" to "ylos")
    private var reuna = arrayOf<Pair<Int, Int>>()

    /**
     * Merkitään annettu solmu osaksi reunaa.
     */
    private fun lisaaReunaan(y: Int, x: Int, ruudukko: Array<Array<Solmu>>) {
        if (x >= 0 &&
            y >= 0 &&
            y < ruudukko.size &&
            x < ruudukko[y].size &&
            ruudukko[y][x].arvo == 0
        ) reuna += Pair(y, x)
    }

    /**
     * Merkitään annettu solmu osaksi ruudukkoa ja käsitellään sen naapurit.
     */
    private fun merkitse(y: Int, x: Int, ruudukko: Array<Array<Solmu>>) {
        // Solmu osaksi labyrinttia.
        ruudukko[y][x].arvo = 1
        lisaaReunaan(y, x - 1, ruudukko)
        lisaaReunaan(y, x + 1, ruudukko)
        lisaaReunaan(y - 1, x, ruudukko)
        lisaaReunaan(y + 1, x, ruudukko)
    }

    /**
     * Lisää annetulle solmulle naapurit sille osoitettuun taulukkoon.
     */
    private fun naapurit(y: Int, x: Int, ruudukko: Array<Array<Solmu>>) {
        if (x > 0 && ruudukko[y][x - 1].arvo != 0) {
            ruudukko[y][x].naapurit += Triple(y, x-1, ruudukko[y][x-1])
        }
        if (x + 1 < ruudukko[y].size && ruudukko[y][x + 1].arvo != 0) {
            ruudukko[y][x].naapurit += Triple(y, x+1, ruudukko[y][x+1])
        }
        if (y > 0 && ruudukko[y - 1][x].arvo != 0) {
            ruudukko[y][x].naapurit += Triple(y-1, x, ruudukko[y-1][x])
        }
        if (y + 1 < ruudukko.size && ruudukko[y + 1][x].arvo != 0) {
            ruudukko[y][x].naapurit += Triple(y+1, x, ruudukko[y+1][x])
        }
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
    fun muunnaLabyrintiksi(ruudukko: Array<Array<Solmu>>): Array<Array<Solmu>> {
        // Lasketaan satunnaisesti koordinaatit lähtöruudulle
        val satunnaisX = ruudukko.indices.random()
        val satunnaisY = ruudukko[0].indices.random()

        // Alustetaan reunaruudukko ja vähennetään lukumäärästä ensimmäinen reunasolmu
        var counter = ruudukko.size * ruudukko[0].size - 1

        // Merkitään ruudukkoon aloitussolmu ja lisätään sen naapurit osaksi reunaa
        merkitse(satunnaisX, satunnaisY, ruudukko)

        // Luupataan niin kauan, kun reunataulukossa on solmuja
        do {
            // Valitaan ja poistetaan sattumalla solmu reunalta
            val reunaSolmu = reuna.random()
            reuna = reuna.filter { it != reunaSolmu }.toTypedArray()
            counter--

            // Rakennetaan naapuritaulukko valitulle reunasolmulle
            naapurit(reunaSolmu.first, reunaSolmu.second, ruudukko)

            // Valitaan naapureista sattumalla solmu
            val naapuri = ruudukko[reunaSolmu.first][reunaSolmu.second].naapurit.random()

            // Lasketaan suunta reunasolmusta naapurisolmuun
            val suunt = suunta(reunaSolmu.first, reunaSolmu.second, naapuri.first, naapuri.second)

            // Merkitään valitun reunasolmun suunnaksi laskettu suunta
            ruudukko[reunaSolmu.first][reunaSolmu.second].suunnat += suunt

            // Merkitään naapurisolmun suunnaksi vastakkaissuunta suhteessa reunasolmuun
            ruudukko[naapuri.first][naapuri.second].suunnat += vastakkainen[suunt]!!

            // Merkitään reunasolmu osaksi ruudukkoa
            merkitse(reunaSolmu.first, reunaSolmu.second, ruudukko)
        } while (counter > 0)

        return ruudukko
    }
}
