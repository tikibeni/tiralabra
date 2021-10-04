# Käyttöohje

## Ohjelman kloonaus
Komentorivillä haluamassasi kohteessa jollakin seuraavista menetelmistä:

SSH: 
```
git clone git@github.com:tikibeni/luolastogen.git
```

HTTPS:
```
git clone https://github.com/tikibeni/luolastogen.git
```

GitHub CLI:
```
gh repo clone tikibeni/luolastogen
```

## Ohjelman asennus

Projektin asennus projektijuuren kautta komennolla: `./gradlew install` ja `./gradlew build`

## Ohjelman ajaminen ja testit

Ohjelman ajaminen projektijuuren kautta komennolla: 
```
./gradlew run
```

JUnit-testien ajaminen projektijuuren kautta komennolla:
```
./gradlew test
```

Lisää testauksesta [täältä](testaus.md)


## Ohjelman käyttö

Tällä hetkellä ohjelman UI:na toimii tekstikäyttöliittymä.
Käynnistyksen myötä ohjelma kysyy käyttäjältä ruudukon mittoja, jonka pohjalta ohjelma alustaa ruudukon labyrintille.
Tämän myötä ohjelma pyöräyttää luodusta ruudukosta labyrintin halutulla algoritmilla (toistaiseksi vain Wilson) ja 
kertoo siihen kuluneen ajan. Toteutettuani molemmat algoritmit ohjelma tulee kysymään kumpaa algoritmia käytetään.
