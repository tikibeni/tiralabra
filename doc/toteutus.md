# Toteutus

## Rakenne

Ohjelmaa ajetaan [Main.kt](../src/main/kotlin/Main.kt)-luokasta käsin. Toistaiseksi ajetaan vain Wilsonin algoritmin 
mukainen ajo käyttäjän syöttämillä pinta-alamuuttujilla. Tavoitteena on rakentaa Main-luokasta käytettävämpi siten, että 
Primin algon valmistuttua käyttäjä pystyy itse päättämään pinta-alan lisäksi käytettävän algon. Algojen valmistuttua
rakennan ohjelmalle [TornadoFX](https://tornadofx.io/) hyödyntävän GUI:n, mistä algoritmien toimintaa voi seurata.

Itse algoritmit sijaitsevat [algo](../src/main/kotlin/algo)-kansiossa. Tällä hetkellä täältä löytyy Wilsonin algoritmi 
ja alustamaton random-Prim.

Labyrintin muodostuksessa hyödynnetään [util](../src/main/kotlin/util)-kansion mukaisia apuluokkia. Tällä hetkellä on
implementoitu vain [Ruutu](../src/main/kotlin/util/Ruutu.kt)-luokka, joka pitää kirjaa yksittäisen ruudukon ruudun
arvosta ja suunnasta. Lisäksi `util`-kansiossa sijaitsee [apufunktiot](../src/main/kotlin/util/Apufunktiot.kt)-luokka,
joka sisältää ohjelman uudelleenkäytettäviä apufunktioita.

## Suorituskyky- ja O-analyysivertailu

Tämä osio tarkentuu molempien algoritmien implementoinnin myötä. Tällä hetkellä arviota Wilsonin algoritmin tehosta voi
sanoa sen verran, että pahimmassa tapauksessa Wilsonin algoritmi voi teoriassa jatkaa ikuisesti tietyillä parametreilla.
Kuten [testausdokumentista](./testaus.md) ilmenee, suorituskykyä testataan ajastimella.

## Puutteet ja parannusehdotukset

TBA projektin lopussa.

## Lähteet

Wilsonin algoritmi on rakennettu [tätä](https://weblog.jamisbuck.org/2011/1/20/maze-generation-wilson-s-algorithm)
lähdettä kun taas RanPrim on rakennettu [tätä](https://weblog.jamisbuck.org/2011/1/10/maze-generation-prim-s-algorithm)
lähdettä mukaillen.

Lisää lähteitä on luettavissa [määrittelydokumentista](./maarittely.md)
