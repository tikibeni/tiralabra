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
    fun kaveleeLoppuun() {
        val wilson = Wilson()
        val ruudukko = wilson.rakennaRuudukko(3, 3)
        wilson.alustaKaydyt(ruudukko)
        val laby = wilson.muunnaLabyrintiksi(ruudukko)
        assertNotNull(laby)
    }
}