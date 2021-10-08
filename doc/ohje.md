# Käyttöohje

## Ohjelman kloonaus
Komentorivillä haluamassasi kohteessa jollakin seuraavista menetelmistä:

SSH: 
```shell
~$ git clone git@github.com:tikibeni/luolastogen.git
```

HTTPS:
```shell
~$ git clone https://github.com/tikibeni/luolastogen.git
```

GitHub CLI:
```shell
gh repo clone tikibeni/luolastogen
```

## Ohjelman asennus

Projektin asennus projektijuuren kautta: 

```shell
~$ cd luolastogen
~/luolastogen$ ./gradlew install
```


## Ohjelman ajaminen ja testit

Ohjelman ajaminen projektijuuren kautta: 

- Konsoliasetukset, jotta tulosteet näkyvät selkeämpänä

```shell
~/luolastogen$ ./gradlew run --console=plain
```

Vaihtoehtoisesti projektin voi ajaa IDE:llä _(esim. Intellij IDEA)_ suorittamalla [Main.kt](../src/main/kotlin/Main.kt).

JUnit-testien ajaminen projektijuuren kautta:

```shell
~/luolastogen$ ./gradlew test
```

Vaihtoehtoisesti testit voi ajaa suorittamalla kaikki testit kansiosta [test](../src/test).

Lisää testauksesta [täältä](testaus.md)


## Ohjelman käyttö

Tällä hetkellä ohjelman UI:na toimii tekstikäyttöliittymä. Tällä hetkellä labyrintti näkyy siispä vielä lukuarvoina 0,
1 ja 2, joista luvun 0 ei tulisi ilmaantua lopputuloksessa, mikäli labyrintti on rakennettu onnistuneesti.
Käynnistyksen myötä ohjelma kysyy käyttäjältä ruudukon mittoja, jonka pohjalta ohjelma alustaa ruudukon labyrintille.
Tämän myötä ohjelma pyöräyttää luodusta ruudukosta labyrintin halutulla algoritmilla (toistaiseksi vain Wilson) ja 
kertoo siihen kuluneen ajan. Toteutettuani molemmat algoritmit ohjelma tulee kysymään kumpaa algoritmia käytetään.
