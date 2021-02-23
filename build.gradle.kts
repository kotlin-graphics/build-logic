plugins {
    `kotlin-dsl`
    `maven-publish`
}

group = "com.github.kotlin_graphics"
version = "0.0.1"

repositories.mavenCentral()

publishing {
    publications.create<MavenPublication>("mavenJava") {
        from(components["java"])
    }
}