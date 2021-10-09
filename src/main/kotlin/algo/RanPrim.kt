package luolastogeneraattori.algo

import luolastogeneraattori.util.*

class RanPrim {
    private var vastakkainen = mapOf("oikea" to "vasen", "vasen" to "oikea", "ylos" to "alas", "alas" to "ylos")
    private var reuna = arrayOf<Array<Ruutu>>()

    private fun kasitteleSeuraava(y: Int, x: Int, ruudukko: Array<Array<Ruutu>>) {
        if (x >= 0 && y >= 0 && y < ruudukko.size && x < ruudukko[y].size && ruudukko[y][x].arvo == 0) {
            ruudukko[y][x].arvo = 1
            reuna[x][y].arvo = 1
        }
    }

    private fun merkitse(y: Int, x: Int, ruudukko: Array<Array<Ruutu>>) {
        ruudukko[y][x].arvo = 1
        kasitteleSeuraava(x - 1, y, ruudukko)
        kasitteleSeuraava(x + 1, y, ruudukko)
        kasitteleSeuraava(x, y - 1, ruudukko)
        kasitteleSeuraava(x, y + 1, ruudukko)
    }

    private fun naapurit(y: Int, x: Int, ruudukko: Array<Array<Ruutu>>): Array<Array<Ruutu>> {
        val naap: Array<Array<Ruutu>> = rakennaRuudukko(3, 3)

        if (x > 0 && ruudukko[y][x - 1].arvo != 0) {
            naap[x - 1][y] = ruudukko[x - 1][y]
        }
        if (x + 1 < ruudukko[y].size && ruudukko[y][x + 1].arvo != 0) {
            naap[x + 1][y] = ruudukko[x + 1][y]
        }
        if (y > 0 && ruudukko[y - 1][x].arvo != 0) {
            naap[x][y - 1] = ruudukko[x][y - 1]
        }
        if (y + 1 < ruudukko.size && ruudukko[y + 1][x].arvo != 0) {
            naap[x][y + 1] = ruudukko[x][y + 1]
        }

        return naap
    }

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
        reuna = alustaKaydyt(ruudukko)
        val satunnaisX = (ruudukko.indices).random()
        val satunnaisY = (ruudukko[0].indices).random()

        merkitse(satunnaisY, satunnaisX, ruudukko)

        do {
            val reunaX = (reuna.indices).random()
            val reunaY = (reuna[0].indices).random()
            reuna[reunaY][reunaX].arvo = 0
            val naap = naapurit(reunaY, reunaX, ruudukko)

            val nx = naap.indices.random()
            val ny = naap.indices.random()

            val suunt = suunta(reunaY, reunaX, ny, nx)
            ruudukko[reunaY][reunaX].suunta = suunt
            ruudukko[ny][nx].suunta = vastakkainen[suunt]

            merkitse(reunaY, reunaX, ruudukko)
        } while (reuna.isNotEmpty())

        return ruudukko
    }
}
