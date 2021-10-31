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

```shell
~/luolastogen$ ./gradlew run
```

Vaihtoehtoisesti projektin voi ajaa IDE:llä _(esim. Intellij IDEA)_ suorittamalla [Main.kt](../src/main/kotlin/Main.kt).

JUnit-testien ajaminen projektijuuren kautta:

```shell
~/luolastogen$ ./gradlew test
```

Vaihtoehtoisesti testiajolle voi lisätä vivun `--info`, joka tulostaa tarkempaa tietoa testiajoista.

Vaihtoehtoisesti testit voi ajaa suorittamalla kaikki testit kansiosta [test](../src/test).

Lisää testauksesta [täältä](testaus.md).


## Ohjelman käyttö

Ohjelman UI:na toimii tekstikäyttöliittymä. Ohjelman käynnistyessä se kysyy käyttäjältä kolmea syötettä:

1. Käytettävä algoritmi:
   1. Kokonaislukusyöte '1' korreloi Wilsonin algoritmia
   2. Kokonaislukusyöte '2' korreloi satunnaistettua Primin algoritmia
   3. Ohjelma huomauttaa mikäli vääränlaista syötettä tarjotaan.
2. Verkon leveys
   1. Valitaan rivikohtainen kokonaislukumuotoinen solmujen määrä
   2. Ohjelma huomauttaa vääränlaisesta syötteestä.
3. Verkon korkeus
   1. Valitaan rivien lukumäärä kokonaislukumuotoisena syötteenä.
   2. Ohjelma jälleen huomauttaa vääränlaisesta syötteestä.

Tämän jälkeen ohjelma muodostaa valitun algoritmin mukaisen verkon, joka piirretään. Lopuksi näytetään käyttäjälle
verkon tiedot ja kulunut aika millisekunneissa.
