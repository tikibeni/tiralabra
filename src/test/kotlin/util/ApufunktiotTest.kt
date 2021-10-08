package util

import luolastogeneraattori.util.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ApufunktiotTest {
    @Test
    fun ruudukonRakennusJaDebug() {
        // Testi ruudukonrakennusmetodille
        val ruudukko = rakennaRuudukko(3, 3)
        assertEquals(ruudukko.size, 3)
        assertEquals(ruudukko[0].size, 3)
    }
}
