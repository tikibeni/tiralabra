package luolastogeneraattori

import luolastogeneraattori.util.*
import luolastogeneraattori.algo.*
import kotlin.system.measureTimeMillis

fun main() {
    println("Tervetuloa labyrinttigeneraattoriin!")

    // Alustetaan ja kysytään käyttäjältä tarvittavat arvot
    val w = Wilson()
    val p = RanPrim()
    val algoLuku = tarkistaLukuSyote("algoritmi", 1..2, "\nKummalla algoritmilla rakennetaan (syötä luku):\n\t1. Wilson\n\t2. Satunnaistettu Prim\n")
    val leveys = tarkistaLukuSyote("leveys", 3..10, "Syötä solmujen lukumäärä riveillä arvoväliltä 3-10: ")
    val korkeus = tarkistaLukuSyote("korkeus", 3..10, "Syötä rivien lukumäärä arvoväliltä 3-10: ")
    val ruudukko = rakennaRuudukko(leveys, korkeus)
    var laby: Array<Array<Solmu>>

    // Muodostetaan labyrintti ja mitataan kesto millisekuntiajastimella
    val kesto = when (algoLuku) {
        1 -> measureTimeMillis {
            laby = w.muunnaLabyrintiksi(ruudukko)
        }
        else -> measureTimeMillis {
            laby = p.muunnaLabyrintiksi(ruudukko)
        }
    }

    // Labyrintin tulostus
    piirraLabyrintti(laby)

    println("Rakentamisessa kesti $kesto ms. \nRuudukossa solmuja: $leveys * $korkeus")
}
