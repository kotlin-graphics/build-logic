import magik.github

plugins {
    `kotlin-dsl` apply false
    //    id("kx.snapshot") version "0.0.5"
    id("elect86.magik") version "0.1.1"
    //    `maven-publish`
}

version = "0.7.1"

repositories {
    mavenCentral()
}

magik { commitWithChanges.set(true) }

subprojects {

    group = "kotlin.graphics.build-logic"
    version = rootProject.version

    //    println(rootProject.plugins.forEach { println(it) })
    apply(plugin = "org.gradle.kotlin.kotlin-dsl")
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "elect86.magik")

    repositories {
        mavenCentral()
        gradlePluginPortal()
        //        maven("https://repo.repsy.io/mvn/elect/kx/")
        github("kotlin-graphics/mary")
    }

    extensions.configure<PublishingExtension> {
        repositories {
            //                        maven {
            //                            name = "prova"
            //                            url = uri("$rootDir/prova")
            //                        }
            github { domain = "kotlin-graphics/mary" }
        }
    }
}
