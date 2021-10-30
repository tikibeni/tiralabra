package luolastogeneraattori

import kotlin.system.measureTimeMillis
import luolastogeneraattori.util.*
import luolastogeneraattori.algo.*

fun main() {
    println("Tervetuloa labyrinttigeneraattoriin!")
    val w = Wilson()
    val p = RanPrim()
    val algoLuku = tarkistaLukuSyote("algoritmi", 1..2, "\nKummalla algoritmilla rakennetaan (syötä luku):\n\t1. Wilson\n\t2. Satunnaistettu Prim\n")
    val leveys = tarkistaLukuSyote("leveys", 3..10, "Syötä ruudukon leveys arvoväliltä 3-10: ")
    val korkeus = tarkistaLukuSyote("korkeus", 3..10, "Syötä ruudukon korkeus arvoväliltä 3-10: ")
    val ruudukko = rakennaRuudukko(leveys, korkeus)
    var laby: Array<Array<Ruutu>>

    val kesto = when (algoLuku) {
        1 -> measureTimeMillis {
            laby = w.muunnaLabyrintiksi(ruudukko)
        }
        else -> measureTimeMillis {
            laby = p.muunnaLabyrintiksi(ruudukko)
        }
    }

    println("Lopullinen ruudukko algoritmilla: ")
    debugRuudukko(laby)

    println("Rakentamisessa kesti $kesto ms. \nRuudukon koko $leveys * $korkeus")
}
