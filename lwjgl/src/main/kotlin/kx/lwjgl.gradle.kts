package kx

plugins {
    `java-library`
}

dependencies {

    val platformVersion = rootProject.extra["platformVersion"]
    implementation(platform("kotlin.graphics.platform:source:$platformVersion"))
}



