package kx

plugins {
    `java-library`
}

dependencies {

    val platformVersion by extra {"platformVersion" }
    implementation(platform("kotlin.graphics.platform:source:$platformVersion"))
}



