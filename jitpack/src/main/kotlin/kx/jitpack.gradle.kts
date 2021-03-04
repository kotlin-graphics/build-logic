import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
}

repositories {
    maven("https://jitpack.io")
}

publishing {
    publications.create<MavenPublication>("mavenJava") {
        from(components["java"])
    }
}