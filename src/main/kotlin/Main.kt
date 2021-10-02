package luolastogeneraattori

import luolastogeneraattori.algo.Wilson
import luolastogeneraattori.util.Ruutu
import kotlin.system.measureTimeMillis

fun main() {
    /* Ohjelmarunkoa:
        1. Pyydä käyttäjältä syötteenä suorakulmion / neliön korkeus ja leveys (voivat olla sama arvo: x²)
        2. Generoi ruudukkopohja
        3. Kysy käyttäjältä kumpaa algoritmia käytetään (kun molemmat algot toteutettu)
        4. Algoritmi muodostaa ruudukosta käytävistön. Näytetään lopputulos käyttäjälle ajan kera.
     */
    val w = Wilson()
    val leveys = 4
    val korkeus = 4
    val ruudukko = w.rakennaRuudukko(leveys, korkeus)
    w.alustaKaydyt(ruudukko)
    println("Alustettu ruudukko leveydellä 3, korkeudella 3")
    var laby: Array<Array<Ruutu>>
    val kesto = measureTimeMillis {
        laby = w.muunnaLabyrintiksi(ruudukko)
    }
    println("Lopullinen ruudukko Wilsonin algoritmilla: ")
    w.debugRuudukko(laby)
    println("Rakentamisessa kesti $kesto ms. \nRuudukon koko $leveys * $korkeus")
}