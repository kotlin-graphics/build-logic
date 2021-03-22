package kx

plugins {
    `java-library`
}

dependencies {

    val platformVersion = rootProject.extra["platformVersion"] ?: "0.2.8+25"
    implementation(platform("kotlin.graphics.platform:source:$platformVersion"))
}



