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

Toistaiseksi ohjelman käyttö rajautuu edellämainittuihin komentoihin. Jatkossa projekti laajenee
siten, että käyttäjä pystyy syöttämään ruudukon pinta-alan sekä käytettävän algoritmin.

Tämän myötä ohjelma pyöräyttää luodusta ruudukosta labyrintin halutulla algoritmilla ja kertoo siihen kuluneen ajan.
