package kx

plugins {
    `java-library`
}

dependencies {

    val platformVersion = project.findProperty("platformVersion") ?: "0.2.8+25"
    implementation(platform("kotlin.graphics.platform:source:$platformVersion"))
}



