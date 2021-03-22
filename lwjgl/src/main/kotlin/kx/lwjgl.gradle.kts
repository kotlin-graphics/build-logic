package kx

plugins {
    `java-library`
}

repositories {
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
}

dependencies {

    val platformVersion = when {
        project.hasProperty("platformVersion") -> project.property("platformVersion")
        else -> "0.2.8+29"
    }
    implementation(platform("kotlin.graphics.platform:source:$platformVersion"))
}



