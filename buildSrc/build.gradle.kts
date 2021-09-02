
plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
}

dependencies {
    val magikVersion = "0.1.9"
    implementation("elect86.magik:elect86.magik.gradle.plugin:$magikVersion")

    val kotlinVersion = "1.5.30"
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:$kotlinVersion")
}