
plugins {
    java
    `kotlin-dsl`
}

dependencies {

//    implementation(platform("kx.platform:plugin:0.0.7"))

//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:1.4.31")
//    implementation("com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin")
}

//gradlePlugin {
//    // Define the plugin
//    plugins.create("11") {
//        id = "kx.kotlin"
//        implementationClass = "kx.kotlin.11"
//    }
//}