package kx

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    //    id("com.example.jacoco")
    id("com.github.johnrengelman.shadow")
}

//group = "com.example.myproduct"

dependencies {

    implementation(platform("com.github.elect86.platforms:source:fe09de54"))

    testImplementation(platform("com.github.elect86.platforms:test:fe09de54"))

//    implementation(kotlin("stdlib-jdk8")) already on classpath?

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