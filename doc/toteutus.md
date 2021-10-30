# Toteutus

## Rakenne

Ohjelmaa ajetaan [Main.kt](../src/main/kotlin/Main.kt)-luokasta käsin. Ohjelmalle syötetään käytettävä algoritmi ja
labyrintin pinta-ala.

Itse algoritmit sijaitsevat [algo](../src/main/kotlin/algo)-kansiossa.

Labyrintin muodostuksessa hyödynnetään [util](../src/main/kotlin/util)-kansion mukaisia apuluokkia. Tällä hetkellä on
implementoitu vain [Ruutu](../src/main/kotlin/util/Ruutu.kt)-luokka, joka pitää kirjaa yksittäisen ruudukon ruudun
arvosta, suunnasta ja tarvittaessa naapureista. Lisäksi `util`-kansiossa sijaitsee 
[apufunktiot](../src/main/kotlin/util/Apufunktiot.kt)-luokka, joka sisältää ohjelman uudelleenkäytettäviä apufunktioita.

## Suorituskyky- ja O-analyysivertailu

Tämä osio tarkentuu molempien algoritmien implementoinnin myötä. Wilsonin algoritmin tehosta voi todeta, että pahimmassa 
tapauksessa se voi teoriassa jatkaa ikuisesti tietyillä parametreilla. Kuten [testausdokumentista](./testaus.md) ilmenee, 
suorituskykyä testataan millisekuntiajastimella.

## Puutteet ja parannusehdotukset

- [TornadoFX](https://tornadofx.io/) hyödyntävän GUI:n, mistä algoritmien toimintaa voi seurata.

## Lähteet

Wilsonin algoritmi on rakennettu [tätä](https://weblog.jamisbuck.org/2011/1/20/maze-generation-wilson-s-algorithm)
lähdettä kun taas RanPrim on rakennettu [tätä](https://weblog.jamisbuck.org/2011/1/10/maze-generation-prim-s-algorithm)
lähdettä mukaillen.

Lisää lähteitä on luettavissa [määrittelydokumentista](./maarittely.md)
