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

    println("Alustettu ruudukko leveydellä $leveys, korkeudella $korkeus\n")
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

/**
 * Piirretään labyrintti lasketusta ruudukosta
 */
fun piirraLabyrintti(labyrintti: Array<Array<Ruutu>>) {
    /*
    1 1 1       # # # # #       # # # # # # #       # # # # # # #
    1 1 1   ->  # 1 1 1 #  ->   # 1   1   1 #  ->   #   #       #
    1 1 1       # 1 1 1 #       #           #       #   #   #   #
                # 1 1 1 #       # 1   1   1 #       #       #   #
                # # # # #       #           #       # # #   #   #
                                # 1   1   1 #       #       #   #
                                # # # # # # #       # # # # # # #

        Mietitääs missä tapauksissa pitää rakentaa seiniä:
        - Jos ruudun alapuolella on naapuri, johon ei ole tai josta ei ole suuntaa
        - Jos ruudun oikealla puolella on naapuri, johon ei ole tai josta ei ole suuntaa
        - Tämän myötä täytyy olla valmis teoriassa piirtämään jokaisen ruudun väliin seinä joko pysty tai vaakasuunnassa
        -> Siispä seinille pitää varata tilaa aina rivi.length - 1 verran
        -> Näin ollen kokonaistilavaativuus on: ruutuLkm + väliseinäVara + ulkoseinät
                                            <-> rivi.length + (rivi.length - 1) + 2
                                            <-> rivi.length * 2 - 1
     */

    // Taulukko, johon tallennetaan piirros
    var piirtoruutu: Array<Array<String>> = arrayOf()

    var vaakaseina: Array<String> = arrayOf()
    for (i in 0 until labyrintti.size * 2 + 1) vaakaseina += "# "

    piirtoruutu += vaakaseina

    labyrintti.forEachIndexed { riviNro, rivi ->
        var piirtorivi: Array<String> = arrayOf()
        var alempipiirto: Array<String> = arrayOf()
        if (riviNro + 1 < labyrintti.size) alempipiirto = arrayOf()

        // Pystyseinää
        piirtorivi += "# "

        rivi.forEachIndexed { sarakeNro, ruutu ->
            // Loogisesti kukin ruutu on itsessään osana reittiä, joten lisätään aina tyhjä.
            piirtorivi += "  "

            // Ensiksi käsitellään rivisuunta (ylös-alas)
            if (riviNro + 1 < labyrintti.size) {
                if (ruutu.suunta == "alas" && labyrintti[riviNro + 1][sarakeNro].suunta == "ylos") {
                    alempipiirto += "  "
                } else alempipiirto += "# "
            }

            // Sitten käsitellään sarakesuunta (vasen-oikea)
            if (sarakeNro + 1 < labyrintti[0].size) {
                if (ruutu.suunta == "oikea" && labyrintti[riviNro][sarakeNro + 1].suunta == "vasen") {
                    piirtorivi += "  "
                } else piirtorivi += "# "
            }
        }
        // Pystyseinää
        piirtorivi += "# "
        piirtoruutu += piirtorivi
    }

    piirtoruutu += vaakaseina

    // Tulostetaan rakennettu labyrintti:
    println("Labyrintti:")


    piirtoruutu.forEach { rivi ->
        rivi.forEach { ruutu ->
            print(ruutu)
        }
        println()
    }
}
