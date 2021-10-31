package luolastogeneraattori.util

/**
 * Solmuun voidaan tallentaa [suunta], [suunnat], [arvo] ja [naapurit].
 *
 * @param [suunta] käytetään Wilsonin algoritmissa
 * @param [suunnat] yksittäinen solmu voi osoittaa useampaan solmuun
 * @param [arvo] indikoi solmun liitostilaa labyrinttiin. 0 = ei käsitelty, 1 = liitetty, 2 = start (Wilson)
 * @param [naapurit] pitää kirjaa viereisistä solmuista ja niiden koordinaateista. Käytetään Primissä.
 */
open class Solmu (
    var suunta: String?,
    var suunnat: Array<String> = arrayOf(),
    var arvo: Int = 0,
    var naapurit: Array<Triple<Int, Int, Solmu>> = arrayOf()
)
