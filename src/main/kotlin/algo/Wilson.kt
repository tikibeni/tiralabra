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
}