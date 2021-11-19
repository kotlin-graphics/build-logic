import magik.github
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    id("org.gradle.kotlin.kotlin-dsl")
    `maven-publish`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish")
    id("elect86.magik")
}

group = rootProject.group

repositories {
    mavenCentral()
    gradlePluginPortal()
    github("kotlin-graphics/mary")
}

project.extensions.getByType<KotlinJvmProjectExtension>().jvmToolchain {
    (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(8))
}