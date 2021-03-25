package kx

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
//    kotlin("jvm")
    id("org.jetbrains.kotlin.jvm")
    //    id("com.example.jacoco")
    id("com.github.johnrengelman.shadow")
}

repositories {
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
}

group = "kotlin.graphics"

dependencies {

    val platformVersion = when {
        project.hasProperty("platformVersion") -> project.property("platformVersion")
        else -> "0.2.8+42"
    }
    implementation(platform("kotlin.graphics.platform:source:$platformVersion"))

    testImplementation(platform("kotlin.graphics.platform:test:$platformVersion"))

    implementation(kotlin("stdlib-jdk8"))

    //    testImplementation("org.junit.jupiter:junit-jupiter-api")
    //    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testImplementation("io.kotest:kotest-runner-junit5")
    testImplementation("io.kotest:kotest-assertions-core")
}

tasks {

    withType<KotlinCompile>().all {
        kotlinOptions.freeCompilerArgs += listOf("-Xinline-classes", "-Xopt-in=kotlin.RequiresOptIn")
    }

    withType<Test> { useJUnitPlatform() }
}

extensions.getByName<JavaPluginExtension>("java").withSourcesJar()