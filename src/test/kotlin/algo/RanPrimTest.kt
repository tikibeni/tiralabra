package algo

import luolastogeneraattori.algo.RanPrim
import luolastogeneraattori.util.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RanPrimTest {
    @Test
    fun kaveleeLoppuun() {
        // Yksinkertainen testi algoritmin läpivedolle. AssertNotNull on enemmänkin muodollisuus, eikä mittaa mitään
        // palautusarvoon liittyvää. Epäonnistuessa tämä testi jäisi ikuiseen luuppiin.
        val prim = RanPrim()
        val ruudukko = rakennaRuudukko(3, 3)
        val laby = prim.muunnaLabyrintiksi(ruudukko)
        assertNotNull(laby)
    }

    @Test
    fun labyrinttiKaikistaRuuduista() {
        // Tarkistaa, ettei lopulliseen ruudukkoon jää yhtään ruutua, jonka arvo olisi 0 tai jolla ei olisi suuntaa
        val prim = RanPrim()
        val ruudukko = rakennaRuudukko(5, 5)
        val laby = prim.muunnaLabyrintiksi(ruudukko)
        for (rivi in laby) {
            for (ruutu in rivi) {
                assertNotEquals(ruutu.arvo, 0)
                assertNotEquals(ruutu.suunnat.size, 0)
            }
        }
    }

    @Test
    fun kestoTesti() {
        // Testaa kaatuuko ohjelma erilaisilla syötteillä
        var counter = 0
        var leveys = 0
        var korkeus = 0

        print("Testataan Primiä eri syötteillä: ")
        try {
            while (counter < 20) {
                val prim = RanPrim()
                leveys = (3..50).random()
                korkeus = (3..50).random()
                val verkko = rakennaRuudukko(leveys, korkeus)
                prim.muunnaLabyrintiksi(verkko)
                counter++
            }
        } catch (e: Exception) { println("\nPrim kaatui syötteillä: $leveys, $korkeus\nVirheviesti: ${e.message}\n") }
        print("Ok! (${counter}/20)")
    }
}