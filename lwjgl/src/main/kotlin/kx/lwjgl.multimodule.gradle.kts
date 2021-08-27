package kx


plugins {
    `java-library`
}

repositories {
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
}

dependencies {

    val platformVersion = "0.3.2"
    implementation(platform("kotlin.graphics.platform:source:$platformVersion"))
}



