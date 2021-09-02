plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
}

dependencies {
    val magikVersion = "0.1.9"
    implementation("elect86.magik:elect86.magik.gradle.plugin:$magikVersion")

//    implementation("org.gradle.kotlin:gradle-kotlin-dsl-plugins:${org.gradle.kotlin.dsl.support.expectedKotlinDslPluginsVersion}")
//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$embeddedKotlinVersion")
}