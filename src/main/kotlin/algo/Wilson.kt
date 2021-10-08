package luolastogeneraattori.algo

import luolastogeneraattori.util.*

class Wilson {
    private var kaydyt = arrayOf<Array<Ruutu>>()
    private var suunnat = arrayOf("ylos", "alas", "oikea", "vasen")
    private var vastakkainen = mapOf("oikea" to "vasen", "vasen" to "oikea", "ylos" to "alas", "alas" to "ylos")
    private var deltaX = mapOf("ylos" to 0, "alas" to 0, "oikea" to 1, "vasen" to -1)
    private var deltaY = mapOf("ylos" to -1, "alas" to 1, "oikea" to 0, "vasen" to 0)

    /**
     * Muodostetaan kuljetusta reitistä monikkomuotoinen taulukko
     */
    private fun rakennaReittiMonikko(alkuX: Int, alkuY: Int): Array<Triple<Int, Int, String>> {
        // Alustetaan reittimonikkolista alkaen alkukoordinaatista
        var reitti = arrayOf<Triple<Int, Int, String>>()
        var x = alkuX
        var y = alkuY

        // Navigoidaan aina reitin seuraavaan ruutuun apulistojen avulla
        while (true) {
            val suunta = kaydyt[y][x].suunta
            if (suunta !== null) {
                reitti += Triple(y, x, suunta)
                y += deltaY[suunta]!!
                x += deltaX[suunta]!!
            } else break
        }

        return reitti
    }

    /**
     * Wilsonin algoritmin satunnaiskävely
     */
    private fun randomKavely(ruudukko: Array<Array<Ruutu>>): Array<Triple<Int, Int, String>> {
        while (true) {
            // Y-akselin koordinaatti (rivi)
            var cy = (ruudukko.indices).random()
            // X-akselin koordinaatti (sarake)
            var cx = (ruudukko[0].indices).random()

            // Jos ruudukon arvo on jo labyrintissa, haetaan uusi koordinaatti
            if (ruudukko[cy][cx].arvo != 0) continue

            // Otetaan reitin alkukoordinaatit talteen
            val alkuY = cy
            val alkuX = cx

            var randomKavely: Boolean

            // Aloitetaan kävely
            do {
                // Oletetaan, että tällä kierroksella osutaan maaliin
                randomKavely = false
                suunnat.shuffle()
                for (suunta in suunnat) {
                    // Lasketaan suunnan osoittaman ruudun koordinaatit
                    val ny = cy + deltaY[suunta]!!
                    val nx = cx + deltaX[suunta]!!

                    println("Satunnaiskävelyn osoittama ruutu: ${ny+1}, ${nx+1}")

                    // Onko naapuriruutu validi (ruudukon sisällä)
                    if (nx >= 0 && ny >= 0 && ny < ruudukko.size && nx < ruudukko[ny].size) {
                        // Validin naapurin löydeltaYttyä asetetaan se poistumisvektoriksi
                        kaydyt[cy][cx].suunta = suunta

                        // Jos naapuriruudussa on jo käyty, niin poistutaan kävelystä.
                        if (ruudukko[ny][nx].arvo == 0) {
                            // Muussa tapauksessa jatketaan kävelyä (kunnes törmätään jo käytyyn ruutuun)
                            // Asetetaan uudet koordinaatit ja indikoidaan kävelyn jatkuvuus
                            cy = ny
                            cx = nx
                            randomKavely = true
                        }

                        break
                    } else println("Osuttiin yli ruudukkorajojen. Haetaan uusi koordinaatti")
                }
            } while (randomKavely)

            // Lopulta rakennetaan ja palautetaan reittimonikko
            return rakennaReittiMonikko(alkuX, alkuY)
        }
    }

    /**
     * Algoritmin ajaminen niin kauan kunnes kaikki ruudut on käyty läpi
     */
    fun muunnaLabyrintiksi(ruudukko: Array<Array<Ruutu>>): Array<Array<Ruutu>> {
        // Alustetaan erillinen käyntejä seuraava taulukko
        kaydyt = alustaKaydyt(ruudukko)

        // Valitaan loppupisteeksi satunnainen piste
        val satunnaisX = (ruudukko.indices).random()
        val satunnaisY = (ruudukko[0].indices).random()

        // Asetetaan loppupisteen arvoksi 2, joka indikoi maaliruutua
        println("Maaliruuduksi on valittu koordinaatit: rivi: ${satunnaisY+1}, sarake: ${satunnaisX+1}")
        ruudukko[satunnaisY][satunnaisX].arvo = 2
        debugRuudukko(ruudukko)

        var ruutujaJaljella = ruudukko.size * ruudukko[0].size - 1
        while (ruutujaJaljella > 0) {
            for (it in randomKavely(ruudukko)) {
                if (ruudukko[it.first][it.second].arvo == 0) {
                    debugReitti(it)
                    // Suunnan osoittama y-koordinaatti (rivi)
                    val ny = it.first + deltaY[it.third]!!
                    // Suunnan osoittama x-koordinaatti (sarake)
                    val nx = it.second + deltaX[it.third]!!

                    ruudukko[it.first][it.second].suunta = it.third
                    ruudukko[it.first][it.second].arvo = 1
                    ruudukko[ny][nx].suunta = vastakkainen[it.third]
                    ruutujaJaljella -= 1

                    if (ruutujaJaljella == 0) break
                    debugRuudukko(ruudukko)
                }
            }
        }

        return ruudukko
    }
}
