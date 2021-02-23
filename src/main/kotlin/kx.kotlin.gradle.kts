
plugins {
    java
    kotlin("jvm") version "1.4.30"
    id("com.github.johnrengelman.shadow").version("6.1.0")
//    id("org.jetbrains.dokka") version "1.4.20"
//    id("docs")
}

group = "com.github.kotlin_graphics"

repositories.mavenCentral()

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

java.withSourcesJar()