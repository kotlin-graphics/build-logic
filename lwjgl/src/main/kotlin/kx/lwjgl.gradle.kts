package kx

plugins {
    `java-library`
}

dependencies {

    val platformVersion: String by project
    implementation(platform("kotlin.graphics.platform:source:$platformVersion"))
}



