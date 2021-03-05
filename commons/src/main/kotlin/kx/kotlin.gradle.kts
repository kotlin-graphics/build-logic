package kx

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm")
    //    id("com.example.jacoco")
    id("com.github.johnrengelman.shadow")
}

//group = "com.example.myproduct"

repositories {
    mavenCentral()
}

dependencies {

    implementation(platform("kx.platform:source:0.0.4"))

    testImplementation(platform("kx.platform:test:0.0.4"))

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