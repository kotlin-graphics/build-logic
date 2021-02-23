
plugins { java }

group = "com.github.kotlin_graphics"

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:1.4.30")
    implementation("org.gradle.kotlin:gradle-kotlin-dsl-plugins:2.1.1")
//    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.30")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.30")
    implementation("com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin:6.1.0")
}

java.withSourcesJar()