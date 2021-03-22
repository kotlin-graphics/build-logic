package kx

plugins {
    `java-library`
}

dependencies {

    val platformVersion = when {
        project.hasProperty("platformVersion") -> project.property("platformVersion")
        else -> "0.2.8+25"
    }
    implementation(platform("kotlin.graphics.platform:source:$platformVersion"))
}



