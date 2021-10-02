package luolastogeneraattori.algo

import luolastogeneraattori.util.Ruutu
import kotlin.random.Random

class Wilson {
    private var kaydyt = arrayOf<Array<Ruutu>>()
    private val ylos = 1
    private val alas = 2
    private val oikea = 4
    private val vasen = 8
    private var suunnat = arrayOf(ylos, alas, oikea, vasen)
    private var dx = mapOf(ylos to 0, alas to 0, oikea to 1, vasen to -1)
    private var dy = mapOf(ylos to -1, alas to 1, oikea to 0, vasen to 0)
    private var vastakkainen = mapOf(oikea to vasen, vasen to oikea, ylos to alas, alas to ylos)

    /**
     * Funktio 2D-ruudukon rakentamiseksi, jossa lopulta ilmenee labyrinttiin lisätyt ruudut
     */
    fun rakennaRuudukko(leveys: Int, korkeus: Int): Array<Array<Ruutu>> {
        var ruudukko = arrayOf<Array<Ruutu>>()
        for (n in 1..leveys) {
            var rivi = arrayOf<Ruutu>()
            for (j in 1..korkeus) {
                rivi += Ruutu(null, 0)
            }
            ruudukko += rivi
        }

        return ruudukko
    }

    /**
     * Alustetaan erillinen taulukko käyntien seuraamiseksi
     */
    fun alustaKaydyt(ruudukko: Array<Array<Ruutu>>) {
        for (n in 0 until ruudukko[0].size) {
            var rivi = arrayOf<Ruutu>()
            for (j in ruudukko.indices) {
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
    private fun debugReitti(reitti: Triple<Int, Int, Int>) {
        println("Reitin debuggaus::")
        println("Koordinaatit rivi: ${reitti.first}, sarake: ${reitti.second}, suunta: ${reitti.third}")
    }

    /**
     * Wilsonin algoritmin satunnaiskävely
     */
    private fun randomKavely(ruudukko: Array<Array<Ruutu>>): Array<Triple<Int, Int, Int>> {
        testi@ while (true) {
            // X-akselin koordinaatti (sarake)
            var cx = Random.nextInt(0, ruudukko[0].size - 1)
            // Y-akselin koordinaatti (rivi)
            var cy = Random.nextInt(0, ruudukko.size - 1)

            println("Satunnaisalkukoordinaatit kävelylle: \n X: $cx \n Y: $cy\nSatunnaisarvo: ${ruudukko[cy][cx].arvo}")

            // Jos ruudukon arvo on jo labyrintissa, haetaan uusi koordinaatti
            if (ruudukko[cy][cx].arvo != 0) continue@testi

            // Otetaan reitin alkukoordinaatit talteen
            val alkuX = cx
            val alkuY = cy

            var randomKavely: Boolean

            do {
                // Oletetaan, että tällä kierroksella osutaan maaliin
                randomKavely = false
                suunnat.shuffle()
                for (suunta in suunnat) {
                    // println("Suunta: $suunta")
                    val nx = cx + dx[suunta]!!
                    val ny = cy + dy[suunta]!!

                    // println("NX: $nx \n NY: $ny")

                    // Onko naapuriruutu validi (ruudukon sisällä)
                    if (nx >= 0 && ny >= 0 && ny < ruudukko.size && nx < ruudukko[ny].size) {
                        // Validin naapurin löydyttyä asetetaan se poistumisvektoriksi
                        kaydyt[cy][cx].suunta = suunta

                        // Jos naapuriruudussa on jo käyty, niin poistutaan kävelystä
                        if (ruudukko[ny][nx].arvo != 0) {
                            break
                        } else {
                            // Muussa tapauksessa jatketaan kävelyä (kunnes törmätään jo käytyyn ruutuun)
                            // Asetetaan uudet koordinaatit ja indikoidaan kävelyn jatkuvuus
                            cx = nx
                            cy = ny
                            randomKavely = true
                            break
                        }
                    }
                }
            } while (randomKavely)

            // println("Poistuttiin kävelystä")

            // Alustetaan reittimonikkolista alkaen alkukoordinaatista
            var reitti = arrayOf<Triple<Int, Int, Int>>()
            var x = alkuX
            var y = alkuY

            // Navigoidaan aina reitin seuraavaan ruutuun apulistojen avulla
            while (true) {
                if (kaydyt[y][x].suunta !== null) {
                    val dir: Int? = kaydyt[y][x].suunta
                    if (dir !== null) {
                        reitti += Triple(y, x, dir)
                        x += dx[dir]!!
                        y += dy[dir]!!
                    }
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
        // Valitaan loppupisteeksi satunnainen piste
        val satunnaisX = Random.nextInt(0, ruudukko.size - 1)
        val satunnaisY = Random.nextInt(0, ruudukko[0].size - 1)

        // Asetetaan loppupisteen arvoksi 2, joka indikoi maaliruutua
        println("Maaliruuduksi on valittu koordinaatit: rivi: ${satunnaisY+1}, sarake: ${satunnaisX+1}")
        ruudukko[satunnaisY][satunnaisX].arvo = 2
        debugRuudukko(ruudukko)

        var ruutujaJaljella = ruudukko.size * ruudukko[0].size - 1
        while (ruutujaJaljella > 0) {
            for (it in randomKavely(ruudukko)) {
                debugReitti(it)
                // Suunnan osoittama y-koordinaatti (rivi)
                val ny = it.first + dy[it.third]!!
                // Suunnan osoittama x-koordinaatti (sarake)
                val nx = it.second + dx[it.third]!!

                ruudukko[it.first][it.second].suunta = it.third
                ruudukko[it.first][it.second].arvo = 1
                ruudukko[ny][nx].suunta = vastakkainen[it.third]
                ruudukko[ny][nx].arvo = 1
                ruutujaJaljella -= 1
                println("Ruutuja jäljellä: $ruutujaJaljella")

                if (ruutujaJaljella == 0) {
                    break
                }

                debugRuudukko(ruudukko)
            }
        }


        return ruudukko
    }
}