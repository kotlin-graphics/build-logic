package kx

plugins {
    `java-library`
}

val platformVersion by extra("platformVersion")

dependencies {

    implementation(platform("kotlin.graphics.platform:source:$platformVersion"))
}



