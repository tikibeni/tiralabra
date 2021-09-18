package luolastogeneraattori.algo

class Wilson {
    /**
     * Funktio 2D-ruudukon rakentamiseksi
     */
    fun rakennaRuudukko(leveys: Int, korkeus: Int): Array<Array<String>> {
        var ruudukko = arrayOf<Array<String>>()
        for (n in 0..leveys) {
            var rivi = arrayOf<String>()
            for (j in 0..korkeus) {
                rivi += "#"
            }
            ruudukko += rivi
        }

        return ruudukko
    }

    /**
     * Varsinaisen algoritmin ajaminen
     */
    fun muunnaLabyrintiksi() {
        /* Kuvausta
           1. Aloitetaan valitsemalla satunnaisesti aloitusruutu ja pidetään kirjaa koskemattomista ruuduista
           2. Suoritetaan algoritmia luupissa niin kauan kun on koskemattomia ruutuja
              2.1. 
           3. Palautetaan labyrintti
         */
    }
}