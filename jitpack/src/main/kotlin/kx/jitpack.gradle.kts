package kx

import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}