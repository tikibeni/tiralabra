# Toteutus

## Rakenne

Ohjelmaa ajetaan [Main.kt](../src/main/kotlin/Main.kt)-luokasta käsin. Toistaiseksi ajetaan vain (keskeneräisen) Wilsonin
algoritmin mukainen ajo ennaltamääritellyillä pinta-alamuuttujilla. Tavoitteena on rakentaa Main-luokasta käytettävämpi
siten, että ruudukon ala sekä käytettävä algoritmi ovat käyttäjän päätettävissä.

Itse algoritmit sijaitsevat [algo](../src/main/kotlin/algo)-kansiossa. Tällä hetkellä täältä löytyy keskeneräinen
Wilsonin algoritmi ja alustamaton random-Prim.

Labyrintin muodostuksessa hyödynnetään [util](../src/main/kotlin/util)-kansion mukaisia apuluokkia. Tällä hetkellä on
implementoitu vain [Ruutu](../src/main/kotlin/util/Ruutu.kt)-luokka, joka pitää kirjaa yksittäisen ruudukon ruudun
arvosta ja suunnasta Wilsonin algoritmia tukeakseen.

## Suorituskyky- ja O-analyysivertailu

Tämä osio tarkentuu molempien algoritmien implementoinnin myötä. Tällä hetkellä arviota Wilsonin algoritmin tehosta voi
sanoa sen verran, että pahimmassa tapauksessa Wilsonin algoritmi voi teoriassa jatkaa ikuisesti tietyillä parametreilla.
Kuten [testausdokumentista](./testaus.md) ilmenee, suorituskykyä testataan ajastimella.

## Puutteet ja parannusehdotukset

TBA projektin lopussa.

## Lähteet

Lähteitä on luettavissa [määrittelydokumentista](./maarittely.md)
