import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    kotlin("jvm") version "1.5.30"
    id("io.gitlab.arturbosch.detekt").version("1.18.0")
    jacoco
    application
}

group = "me.bblinnik"
version = "1.0-SNAPSHOT"

// Plugin-repositorio
repositories {
    mavenCentral()
}

application {
    mainClass.set("luolastogeneraattori.MainKt")
}

// Projektin riippuvuudet
dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.junit.jupiter:junit-jupiter:5.8.0")
    testImplementation(kotlin("test"))
}

jacoco {
    toolVersion = "0.8.7"
    applyTo(tasks.run.get())
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

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = "11"
}

// Testeihin käytetään JUnitia, jonka pohjalta luodaan raportti
tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

// Jacoco-raportin määrittely
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    // Muodostetaan toistaiseksi vain html-muotoinen raportti
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}