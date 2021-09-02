import magik.github

plugins {
//    id("org.gradle.kotlin.kotlin-dsl")
    //    kotlin("jvm")// version "1.5.30"
    id("elect86.magik")
    `maven-publish`
    `java-library`
}

version = rootProject.version
group = rootProject.group

repositories {
    mavenCentral()
    gradlePluginPortal()
    github("kotlin-graphics/mary")
}

//magik { commitWithChanges.set(true) }

publishing {
    publications {
        repositories {
            //            maven {
            //                name = "prova"
            //                url = uri("$rootDir/prova")
            //            }
            github { domain = "kotlin-graphics/mary" }
        }
    }
}
