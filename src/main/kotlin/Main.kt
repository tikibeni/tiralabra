package luolastogeneraattori

import luolastogeneraattori.algo.Wilson
import luolastogeneraattori.util.Ruutu
import java.lang.NumberFormatException
import kotlin.system.measureTimeMillis

fun tarkistaLukuSyote(nimi: String, arvovali: IntRange): Int {
    var haluttuSyote: Int
    while (true) {
        print("Syötä ruudukon $nimi arvoväliltä $arvovali: ")
        val syote = readLine()
        try {
            haluttuSyote = Integer.valueOf(syote)
            if (haluttuSyote in arvovali) {
                return haluttuSyote
            } else {
                println("Syötetty luku ei kuulu arvoväliin $arvovali")
            }
        } catch (e: NumberFormatException) {
            println("Arvon $nimi tulee olla kokonaisluku")
        }
    }
}

fun main() {
    /* Ohjelmarunkoa:
        1. Pyydä käyttäjältä syötteenä suorakulmion / neliön korkeus ja leveys (voivat olla sama arvo: x²)
        2. Generoi ruudukkopohja
        3. Kysy käyttäjältä kumpaa algoritmia käytetään (kun molemmat algot toteutettu)
        4. Algoritmi muodostaa ruudukosta käytävistön. Näytetään lopputulos käyttäjälle ajan kera.
     */
    val w = Wilson()
    val leveys = tarkistaLukuSyote("leveys", 3..10)
    val korkeus = tarkistaLukuSyote("korkeus", 3..10)
    val ruudukko = w.rakennaRuudukko(leveys, korkeus)
    println("Alustettu ruudukko leveydellä $leveys, korkeudella $korkeus")
    w.debugRuudukko(ruudukko)
    var laby: Array<Array<Ruutu>>
    val kesto = measureTimeMillis {
        laby = w.muunnaLabyrintiksi(ruudukko)
    }
    println("Lopullinen ruudukko Wilsonin algoritmilla: ")
    w.debugRuudukko(laby)
    println("Rakentamisessa kesti $kesto ms. \nRuudukon koko $leveys * $korkeus")
}
