package luolastogeneraattori.util

/**
 * Funktio 2D-ruudukon rakentamiseksi, jossa lopulta ilmenee labyrinttiin lisätyt ruudut
 */
fun rakennaRuudukko(leveys: Int, korkeus: Int): Array<Array<Ruutu>> {
    var ruudukko = arrayOf<Array<Ruutu>>()
    // Rivejä (korkeus)
    for (n in 1..korkeus) {
        var rivi = arrayOf<Ruutu>()
        // Rivissä ruutuja (leveys)
        for (j in 1..leveys) { rivi += Ruutu(null, 0, arrayOf()) }
        ruudukko += rivi
    }

    println("Alustettu ruudukko leveydellä $leveys, korkeudella $korkeus")
    return ruudukko
}

/**
 * Alustetaan erillinen taulukko käyntien seuraamiseksi
 *
 * @param [ruudukko] johon perustuen alustetaan
 */
fun alustaKaydyt(ruudukko: Array<Array<Ruutu>>): Array<Array<Ruutu>> {
    var kaydyt = arrayOf<Array<Ruutu>>()

    for (n in 1..ruudukko.size) {
        var rivi = arrayOf<Ruutu>()
        for (j in 1..ruudukko[0].size) {
            val r = Ruutu(null, 0, arrayOf())
            rivi += r
        }
        kaydyt += rivi
    }

    return kaydyt
}

/**
 * Annetun ruudukon tulostamista varten
 */
fun debugRuudukko(ruudukko: Array<Array<Ruutu>>) {
    ruudukko.forEach { rivi ->
        rivi.forEach { arvo -> print("${arvo.arvo} ") }
        println()
    }
}

/**
 * Reitin tulostamista varten
 */
fun debugReitti(reitti: Triple<Int, Int, String>) {
    println("Reitin debuggaus.")
    println("Koordinaatit rivi: ${reitti.first+1}, sarake: ${reitti.second+1}, suunta: ${reitti.third}")
}

/**
 * Lukusyötteen validointia varten
 */
fun tarkistaLukuSyote(nimi: String, arvovali: IntRange, viesti: String): Int {
    var haluttuSyote: Int
    while (true) {
        print(viesti)
        val syote = readLine()
        try {
            haluttuSyote = Integer.valueOf(syote)
            if (haluttuSyote in arvovali) return haluttuSyote
            println("Syötetty luku ei kuulu arvoväliin $arvovali\n")
        } catch (e: NumberFormatException) { println("Arvon $nimi tulee olla kokonaisluku") }
    }
}
