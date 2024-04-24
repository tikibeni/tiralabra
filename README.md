# Luolastogeneraattori

Tekemäni harjoitustyö kurssilla **[TKT20010](https://studies.helsinki.fi/opintotarjonta/cu/hy-CU-118025627-2021-08-01) 
(Aineopintojen harjoitustyö: Tietorakenteet ja algoritmit)** lukuvuoden 2021-2022 1. periodilla.

## Lataaminen ja käyttö (tl;dr)

1. Kloonaa
2. Asenna
3. Ajaminen ja testaus

```shell
~$ git clone https://github.com/tikibeni/luolastogen.git
~$ cd luolastogen
~/luolastogen$ ./gradlew install
~/luolastogen$ ./gradlew run
~/luolastogen$ ./gradlew test
```

Tarkemmin [täällä](./doc/ohje.md).

Vaihtoehtoisesti voit ajaa sovelluksen Docker-kontista:

```sh
~/luolastogen$ docker build . -t tira && docker run -it tira
```

## Dokumentaatiota

[Linkki](./doc/maarittely.md) määrittelydokumenttiin.

[Linkki](./doc/ohje.md) käyttö- ja asennusohjeeseen.

[Linkki](./doc/testaus.md) testausdokumenttiin.

[Linkki](./doc/toteutus.md) toteutusdokumenttiin.

[Linkki](./doc/tuntikirjanpito.md) tuntikirjanpitoon.

### Viikkoraportit

* [Viikko 1](doc/weekly/viikkoraportti-1.md)
* [Viikko 2](doc/weekly/viikkoraportti-2.md)
* [Viikko 3](doc/weekly/viikkoraportti-3.md)
* [Viikko 4](doc/weekly/viikkoraportti-4.md)
* [Viikko 5](doc/weekly/viikkoraportti-5.md)
* [Viikko 6](doc/weekly/viikkoraportti-6.md)
