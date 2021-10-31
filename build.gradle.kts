import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.gitlab.arturbosch.detekt.Detekt


// Sovelluksen tarvitsemat lisäosat
plugins {
    kotlin("jvm") version "1.5.30"
    id("io.gitlab.arturbosch.detekt").version("1.18.0")
    jacoco
    application
}


// Sovelluksen pakolliset lisätietomäärittelyt
group = "me.bblinnik"
version = "1.0-SNAPSHOT"


// Repositorio, josta lisäosat haetaan
repositories {
    mavenCentral()
}

// Projektin riippuvuudet
dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.junit.jupiter:junit-jupiter:5.8.0")
    testImplementation(kotlin("test"))
}

// Applikaatioasetukset
application {
    mainClass.set("luolastogeneraattori.MainKt")
}

// Detekt-asetukset
// kts. https://github.com/detekt/detekt#with-gradle
detekt {
    buildUponDefaultConfig = true
    allRules = false
    config = files("$projectDir/config/detekt.yml")
    baseline = file("$projectDir/config/baseline.xml")

    reports {
        html.enabled = true
        xml.enabled = false
        txt.enabled = false
        sarif.enabled = false
    }
}

// Jacoco-asetukset
jacoco {
    toolVersion = "0.8.7"
}


// Build-asetukset (./gradlew build)
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

// Detektin käännösajan asetukset (./gradlew detekt)
tasks.withType<Detekt>().configureEach {
    jvmTarget = "11"
}

// Ajoasetukset (./gradlew run)
tasks.getByName("run", JavaExec::class) {
    // Jotta ohjelman ajaminen komentoriviltä onnistuu syötteillä
    standardInput = System.`in`
}

// Testiasetukset (./gradlew test) - käytetään JUnit, josta luodaan raportti
tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

// Jacoco-raportin määrittely (yhdistetty testiajoon)
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    // Muodostetaan toistaiseksi vain html-muotoinen raportti
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

// Buildauksen yhteydessä säännöt .jar-tiedoston muodostamiseen
tasks.jar {
    manifest {
        attributes["Main-Class"] = "luolastogeneraattori.MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}