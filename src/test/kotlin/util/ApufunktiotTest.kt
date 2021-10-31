package util

import luolastogeneraattori.util.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ApufunktiotTest {
    @Test
    fun ruudukonRakennusJaDebug() {
        // Testi ruudukonrakennusmetodille
        val ruudukko = rakennaRuudukko(3, 3)
        assertEquals(ruudukko.size, 3)
        assertEquals(ruudukko[0].size, 3)
    }

    @Test
    fun labyrintinPiirto() {
        // Testataan piirtokykyä 3x3 verkolla
        var verkko: Array<Array<Solmu>> = arrayOf()
        verkko += arrayOf(
            Solmu(null, arrayOf("alas", "oikea"), 1, arrayOf()),
            Solmu(null, arrayOf("vasen", "oikea"), 1, arrayOf()),
            Solmu(null, arrayOf("vasen"), 1, arrayOf()),
        )

        verkko += arrayOf(
            Solmu(null, arrayOf("ylos", "oikea", "alas"), 1, arrayOf()),
            Solmu(null, arrayOf("vasen", "alas", "oikea"), 1, arrayOf()),
            Solmu(null, arrayOf("vasen"), 1, arrayOf()),
        )

        verkko += arrayOf(
            Solmu(null, arrayOf("ylos"), 1, arrayOf()),
            Solmu(null, arrayOf("ylos", "oikea"), 1, arrayOf()),
            Solmu(null, arrayOf("vasen"), 1, arrayOf()),
        )

        var odotuslopputulos: Array<Array<String>> = arrayOf()
        odotuslopputulos += arrayOf("# ", "# ", "# ", "# ", "# ", "# ", "# ")
        odotuslopputulos += arrayOf("# ", "  ", "  ", "  ", "  ", "  ", "# ")
        odotuslopputulos += arrayOf("# ", "  ", "# ", "# ", "# ", "# ", "# ")
        odotuslopputulos += arrayOf("# ", "  ", "  ", "  ", "  ", "  ", "# ")
        odotuslopputulos += arrayOf("# ", "  ", "# ", "  ", "# ", "# ", "# ")
        odotuslopputulos += arrayOf("# ", "  ", "# ", "  ", "  ", "  ", "# ")
        odotuslopputulos += arrayOf("# ", "# ", "# ", "# ", "# ", "# ", "# ")

        assertArrayEquals(odotuslopputulos, piirraLabyrintti(verkko))
    }
}
