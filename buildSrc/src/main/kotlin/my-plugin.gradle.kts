import magik.github
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    //    id("org.gradle.kotlin.kotlin-dsl") version "1.5.30"
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

tasks {
    withType<KotlinCompile> {
        targetCompatibility = "11"
        sourceCompatibility = "11"
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

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
