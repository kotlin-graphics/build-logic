plugins {
    `kotlin-dsl` apply false
    id("kx.snapshot") version "0.0.5"
}

version = "0.7.0+84" // for ::bump
ext["platformVersion"] = "0.2.8+42"

subprojects {

    group = "kotlin.graphics.build-logic"
    version = when (name) {
        "dev" -> "0.0.5"
        else -> rootProject.version
    }

    apply(plugin = "org.gradle.kotlin.kotlin-dsl")
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
        gradlePluginPortal()
        //        maven("https://repo.repsy.io/mvn/elect/kx/")
        maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
    }

    // limited dsl support inside here
    extensions.configure<PublishingExtension>("publishing") {
        repositories.maven {
            url = uri("$rootDir/../mary")
        }
    }
}