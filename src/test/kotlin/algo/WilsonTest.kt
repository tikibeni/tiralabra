package algo

import luolastogeneraattori.algo.Wilson
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class WilsonTest {
    @Test
    fun ruudukonRakennusJaDebug() {
        // Testi ruudukonrakennusmetodille
        val wilson = Wilson()
        val ruudukko = wilson.rakennaRuudukko(3, 3)
        assertEquals(ruudukko.size, 3)
        assertEquals(ruudukko[0].size, 3)
        print("Testattiin ruudukko ${wilson.debugRuudukko(ruudukko)}")
    }

    @Test
    fun kaveleeLoppuun() {
        // Yksinkertainen testi algoritmin läpivedolle. AssertNotNull on enemmänkin muodollisuus, eikä mittaa mitään
        // palautusarvoon liittyvää. Epäonnistuessa tämä testi jäisi ikuiseen luuppiin.
        val wilson = Wilson()
        val ruudukko = wilson.rakennaRuudukko(3, 3)
        val laby = wilson.muunnaLabyrintiksi(ruudukko)
        assertNotNull(laby)
    }

    @Test
    fun labyrinttiKaikistaRuuduista() {
        // Tarkistaa, ettei lopulliseen ruudukkoon jää yhtään ruutua, jonka arvo olisi 0
        val wilson = Wilson()
        val ruudukko = wilson.rakennaRuudukko(5, 5)
        val laby = wilson.muunnaLabyrintiksi(ruudukko)
        for (rivi in laby) {
            for (ruutu in rivi) {
                assertNotEquals(ruutu.arvo, 0)
            }
        }
    }
}
