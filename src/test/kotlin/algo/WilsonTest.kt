package algo

import luolastogeneraattori.algo.Wilson
import luolastogeneraattori.util.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class WilsonTest {
    @Test
    fun kaveleeLoppuun() {
        // Yksinkertainen testi algoritmin läpivedolle. AssertNotNull on enemmänkin muodollisuus, eikä mittaa mitään
        // palautusarvoon liittyvää. Epäonnistuessa tämä testi jäisi ikuiseen luuppiin.
        val wilson = Wilson()
        val ruudukko = rakennaRuudukko(3, 3)
        val laby = wilson.muunnaLabyrintiksi(ruudukko)
        assertNotNull(laby)
    }

    @Test
    fun labyrinttiKaikistaRuuduista() {
        // Tarkistaa, ettei lopulliseen ruudukkoon jää yhtään ruutua, jonka arvo olisi 0
        val wilson = Wilson()
        val ruudukko = rakennaRuudukko(5, 5)
        val laby = wilson.muunnaLabyrintiksi(ruudukko)
        for (rivi in laby) {
            for (ruutu in rivi) {
                assertNotEquals(ruutu.arvo, 0)
            }
        }
    }
}
