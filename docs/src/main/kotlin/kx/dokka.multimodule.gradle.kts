import org.gradle.kotlin.dsl.`java-library`
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask

plugins {
    `java-library`
    id("org.jetbrains.dokka")
}

repositories {
    maven("https://dl.bintray.com/kotlin/kotlinx")
}

tasks {
    // no dsl support here
    named<DokkaMultiModuleTask>("dokkaHtmlMultiModule") {
        outputDirectory.set(buildDir.resolve("dokkaCustomMultiModuleOutput"))
    }
}