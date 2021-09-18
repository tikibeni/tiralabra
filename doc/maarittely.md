# Määrittelydokumentti

#### Aihe: Luolastogeneraattori: algoritmivertailua
#### Tekijä: Benjamin Blinnikka, tietojenkäsittelytieteen kandidaattiohjelma
#### Kieli: Suomi

### Motivaatio

Luolageneraattori kiinnostaa aiheena ensimmäisenä askeleneena kohti peliohjelmointia. Näen projektin
mahdollisuutena perehtyä peliohjelmoinnin tasogenerointiin, ja tavoitteena olisikin saada tästä jonkinlaista pohjaa
mahdollisia tulevia vapaa-ajan projekteja varten. Ohessa projekti on täydellinen mahdollisuus luoda ensimmäinen Kotlin-
pohjainen projekti Githubiini.

### Työkalut

Käytän projektin rakentamiseen Kotlinia, joka on tullut tutuksi viimeisen puolen vuoden aikana töissä. Kotlinia voisin
kuvailla toimivan jossain määrin kätevämmin kuin perinteisen Javan. Tiedostan, että Kotlin on astetta harvinaisempi
ohjelmointikieli, mutta samalla myös tiedostan kurssilla olevan minun lisäksi ainakin yksi henkilö, joka käyttää
projektissaan Kotlinia. Lisätietoa Kotlinista [täältä](https://kotlinlang.org/). Projektin build-ohjelmistona toimii 
Gradle. Ohessa hallitsen Javan käytön ja tunnen pintapuolin Pythonin.

### Tavoite

#### Viikon 1 rajaus

Projektina toimii luolastogeneraattorin rakentaminen, joka tulee koostumaan lähtökohtaisesti kolmesta osasta:

1. Huoneiden rakentaminen / muodostaminen ja levittäminen
2. Huoneiden välisten käytävien rakentaminen
3. Ulkoasun hiominen, filtteröidään ylimääräiset huoneet pois

Tavoitteellisesti ohjelmalle voisi antaa (jollain tavalla) syötteenä luolaston koon, eli kuinka monesta luolasta
kokonaisuus muodostetaan. Tämän myötä ohjelma muodostaisi satunnaisesti eri kokoisia suorakulmioita, jotka toimivat
huoneina, ja asettaa ne satunnaisiin kohtiin "karttaruudukkoa" siten, etteivät huoneet osu missään kohdin toistensa
päälle. Seuraavaksi ohjelma määrittää huoneiden välille reitit siten, että huoneesta olisi reitti toiseen huoneeseen.
Lopuksi ohjelman muodostaessa annetun lukumäärän luolia välille reitit, tarkistetaan jääkö karttaan luolia, joihin
yksikään reitti ei koske ja siivotaan nämä pois kartalta.

#### Viikon 2 rajaus

Ensimmäisen viikon palautteen ja projektin seuraavien askeleiden pohtimisen myötä olen rajannut tämän kurssin aikaiseksi
tavoitteeksi rakentaa pari-kolme käytävistönmuodostuslalgoritmia, joita sitten vertailen. Tämän myötä ohjelman toimivuus
rajautuu siten, että laajempien huoneiden luolastojen sijaan generoidaan varsinaisia labyrintteja, joiden suoritusaikoja
näytetään ja verrataan. En tuntunut pääsevän aivan alkuun kokonaisuuden hahmottamisen kanssa, joten nykyinen rajaus on
huomattavasti kustannustehokkaampi (ja järkevämpi kanditutkielman kirjoittelun ohessa) ajankäytön osalta.

##### Jatkokehitys

Viikon 2 rajauksen myötä ohjelmaa voisi myöhemmin laajentaa ensimmäisen rajauksen kaltaiseksi mikäli aikaa ja intoa
liikenee joskus tulevaisuudessa.

### Tietorakenteet ja algoritmit

Ohjelmassa käytetään lähtökohtaisesti Wilsonin algoritmia sekä satunnaistetutua Primin algoritmia. Tietorakenteet tulevat
nykyisen näkemykseni mukaan olemaan hyvin vahvasti taulukko- ja mappirakenteisia. Tietorakenneosio tarkentuu
implementaation myötä.

#### Vaativuudet

Wilsonin algoritmin vaativuus ei ole niin suoraviivainen, joten sitä pitää vielä tutkia.

Satunnaistetun Primin algoritmin aikavaativuus äärellisessä ruudukossa on O(n²)

### Läpikäytyjä lähteitä

[Maze generation algorithm - Wikipedia.org](https://en.wikipedia.org/wiki/Maze_generation_algorithm)

[Procedural Dungeon Generation Algorithm](
https://www.gamedeveloper.com/programming/procedural-dungeon-generation-algorithm)

[Procedural Dungeon Generation Algorithm Explained](
https://www.reddit.com/r/gamedev/comments/1dlwc4/procedural_dungeon_generation_algorithm_explained/)

[Rooms and Mazes: A Procedural Dungeon Generator](https://journal.stuffwithstuff.com/2014/12/21/rooms-and-mazes/)

[How to code your own procedural dungeon map generator using the Random Walk Algorithm](
https://www.freecodecamp.org/news/how-to-make-your-own-procedural-dungeon-map-generator-using-the-random-walk-algorithm-e0085c8aa9a/
)