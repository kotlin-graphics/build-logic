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
//    maven("https://repo.repsy.io/mvn/elect/kx")
}

group = "kotlin.graphics"

dependencies {

    val g = "kotlin.graphics.build-logic"
    val v = "0.7.0+26"
    implementation(platform("$g:platform-source:$v"))

    testImplementation(platform("$g:platform-test:$v"))

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