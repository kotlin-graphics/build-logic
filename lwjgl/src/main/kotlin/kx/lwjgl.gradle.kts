package kx

plugins {
    `java-library`
}

dependencies {

    println("lwjgl")
    val platformVersion by rootProject.extra {"platformVersion" }
    implementation(platform("kotlin.graphics.platform:source:$platformVersion"))
}



