
plugins {
    java
    `kotlin-dsl`
}

dependencies {

    implementation(platform("com.github.elect86.platforms:plugin:fe09de54"))

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin")
}

//gradlePlugin {
//    // Define the plugin
//    plugins.create("11") {
//        id = "kx.kotlin"
//        implementationClass = "kx.kotlin.11"
//    }
//}