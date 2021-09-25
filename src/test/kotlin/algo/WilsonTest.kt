package algo

import luolastogeneraattori.algo.Wilson
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class WilsonTest {
    private val wilson = Wilson()

    @Test
    fun ruudukonRakennusJaDebug() {
        val ruudukko = wilson.rakennaRuudukko(3, 3)
        assertEquals(ruudukko.size, 3)
        assertEquals(ruudukko[0].size, 3)
        print("Testattiin ruudukko ${wilson.debugRuudukko(ruudukko)}")
    }

    @Test
    fun alustaKaydyt() {
        // Katso onko tämä todella tarpeellinen.
    }

    @Test
    fun muunnaLabyrintiksi() {
        // TODO kun algoritmi toimii odotetulla tavalla
    }
}