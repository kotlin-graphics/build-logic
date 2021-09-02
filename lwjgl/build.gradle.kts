
plugins {
    `java-gradle-plugin`
    id("org.jetbrains.kotlin.jvm") version "1.5.30"
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "0.15.0"
    `maven-publish`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {

    val platformVersion = "0.3.3+19"
    "implementation"(platform("kotlin.graphics.platform:plugin:$platformVersion"))
}