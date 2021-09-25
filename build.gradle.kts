import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    kotlin("jvm") version "1.5.30"
    id("io.gitlab.arturbosch.detekt").version("1.18.0")
    jacoco
}

group = "me.bblinnik"
version = "1.0-SNAPSHOT"

// Plugin-repositorio
repositories {
    mavenCentral()
}

// Projektin riippuvuudet
dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.junit.jupiter:junit-jupiter:5.8.0")
    testImplementation(kotlin("test"))
}

// Testeihin käytetään JUnitia, jonka pohjalta luodaan raportti
tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
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
        xml.enabled = true
        txt.enabled = true
        sarif.enabled = true
    }
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = "11"
}