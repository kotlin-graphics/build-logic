package kx


plugins {
    `java-library`
}

repositories {
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
}

dependencies {

    val platformVersion = "0.3.3+5"
    implementation(platform("kotlin.graphics.platform:source:$platformVersion"))
}



