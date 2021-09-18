package luolastogeneraattori

import luolastogeneraattori.algo.Wilson

fun main() {
    /* Ohjelmarunkoa:
     1. Pyydä käyttäjältä syötteenä suorakulmion / neliön korkeus ja leveys (voivat olla sama arvo: x²)
     2. Generoi ruudukko risuaidoilla ja viivoilla
     3. Kysy käyttäjältä kumpaa algoritmia käytetään
     4. Algoritmi muodostaa ruudukosta käytävistön. Näytetään lopputulos käyttäjälle ajan kera.
     */
    println("Hello world. This is the beginning of Tiralabra. \n")
    val w = Wilson()
    val ruudukko = w.rakennaRuudukko(5, 5)

    ruudukko.forEach { rivi ->
        rivi.forEach { arvo ->
            print("$arvo ")
        }
        println()
    }
}