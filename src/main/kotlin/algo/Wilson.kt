package luolastogeneraattori.algo

import luolastogeneraattori.util.Ruutu

class Wilson {
    private var kaydyt = arrayOf<Array<Ruutu>>()
    private var suunnat = arrayOf("ylos", "alas", "oikea", "vasen")
    private var vastakkainen = mapOf("oikea" to "vasen", "vasen" to "oikea", "ylos" to "alas", "alas" to "ylos")
    private var dx = mapOf("ylos" to 0, "alas" to 0, "oikea" to 1, "vasen" to -1)
    private var dy = mapOf("ylos" to -1, "alas" to 1, "oikea" to 0, "vasen" to 0)

    /**
     * Funktio 2D-ruudukon rakentamiseksi, jossa lopulta ilmenee labyrinttiin lisätyt ruudut
     */
    fun rakennaRuudukko(leveys: Int, korkeus: Int): Array<Array<Ruutu>> {
        var ruudukko = arrayOf<Array<Ruutu>>()
        // Rivejä (korkeus)
        for (n in 1..korkeus) {
            var rivi = arrayOf<Ruutu>()
            // Rivissä ruutuja (leveys)
            for (j in 1..leveys) {
                rivi += Ruutu(null, 0)
            }
            ruudukko += rivi
        }

        return ruudukko
    }

    /**
     * Alustetaan erillinen taulukko käyntien seuraamiseksi
     */
    private fun alustaKaydyt(ruudukko: Array<Array<Ruutu>>) {
        for (n in 1..ruudukko.size) {
            var rivi = arrayOf<Ruutu>()
            for (j in 1..ruudukko[0].size) {
                val r = Ruutu(null, 0)
                rivi += r
            }
            kaydyt += rivi
        }
    }

    /**
     * Annetun ruudukon tulostamista varten
     */
    fun debugRuudukko(ruudukko: Array<Array<Ruutu>>) {
        ruudukko.forEach { rivi ->
            rivi.forEach { arvo ->
                print("${arvo.arvo} ")
            }
            println()
        }
    }

    /**
     * Reitin tulostamista varten
     */
    private fun debugReitti(reitti: Triple<Int, Int, String>) {
        println("Reitin debuggaus.")
        println("Koordinaatit rivi: ${reitti.first+1}, sarake: ${reitti.second+1}, suunta: ${reitti.third}")
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


            println("Kävely alkaa pisteestä: \n rivi: ${cy+1} \n sarake: ${cx+1}")

            // Jos ruudukon arvo on jo labyrintissa, haetaan uusi koordinaatti
            if (ruudukko[cy][cx].arvo != 0) {
                println("Osuttiin suoraan reittiin - haetaan uusi alkukoordinaatti.")
                continue
            }

            // Otetaan reitin alkukoordinaatit talteen
            val alkuY = cy
            val alkuX = cx

            var randomKavely: Boolean

            do {
                // Oletetaan, että tällä kierroksella osutaan maaliin
                randomKavely = false
                suunnat.shuffle()
                for (suunta in suunnat) {
                    // Lasketaan suunnan osoittaman ruudun koordinaatit
                    val ny = cy + dy[suunta]!!
                    val nx = cx + dx[suunta]!!

                    println("Satunnaiskävelyn osoittama ruutu: ${ny+1}, ${nx+1}")

                    // Onko naapuriruutu validi (ruudukon sisällä)
                    if (nx >= 0 && ny >= 0 && ny < ruudukko.size && nx < ruudukko[ny].size) {
                        // Validin naapurin löydyttyä asetetaan se poistumisvektoriksi
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
                    } else {
                        println("Osuttiin yli ruudukkorajojen. Haetaan uusi koordinaatti")
                    }
                }
            } while (randomKavely)

            println("Osuttiin reitillä olevaan ruutuun / maaliruutuun")

            // Alustetaan reittimonikkolista alkaen alkukoordinaatista
            var reitti = arrayOf<Triple<Int, Int, String>>()
            var x = alkuX
            var y = alkuY

            // Navigoidaan aina reitin seuraavaan ruutuun apulistojen avulla
            while (true) {
                val suunta = kaydyt[y][x].suunta
                if (suunta !== null) {
                    reitti += Triple(y, x, suunta)
                    y += dy[suunta]!!
                    x += dx[suunta]!!
                } else {
                    break
                }
            }

            // Lopulta palautetaan reitti
            return reitti
        }
    }

    /**
     * Algoritmin ajaminen niin kauan kunnes kaikki ruudut on käyty läpi
     */
    fun muunnaLabyrintiksi(ruudukko: Array<Array<Ruutu>>): Array<Array<Ruutu>> {
        // Alustetaan erillinen käyntejä seuraava taulukko
        alustaKaydyt(ruudukko)

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
                    val ny = it.first + dy[it.third]!!
                    // Suunnan osoittama x-koordinaatti (sarake)
                    val nx = it.second + dx[it.third]!!

                    ruudukko[it.first][it.second].suunta = it.third
                    ruudukko[it.first][it.second].arvo = 1
                    ruudukko[ny][nx].suunta = vastakkainen[it.third]
                    ruutujaJaljella -= 1
                    println("Ruutuja jäljellä: $ruutujaJaljella")

                    if (ruutujaJaljella == 0) {
                        break
                    }

                    debugRuudukko(ruudukko)
                }

            }
        }


        return ruudukko
    }
}
