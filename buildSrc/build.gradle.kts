import org.gradle.kotlin.dsl.support.expectedKotlinDslPluginsVersion

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
}

dependencies {

    val platformVersion = "0.3.6"
    implementation(platform("kotlin.graphics:platform-plugin:$platformVersion"))

    implementation("elect86.magik:elect86.magik.gradle.plugin")

    implementation("org.gradle.kotlin:gradle-kotlin-dsl-plugins:$expectedKotlinDslPluginsVersion")

    implementation("com.gradle.plugin-publish:com.gradle.plugin-publish.gradle.plugin:0.16.0")

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$embeddedKotlinVersion")
}

kotlin.jvmToolchain {
    (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(8))
}