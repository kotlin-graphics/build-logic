//import java.io.ByteArrayOutputStream

plugins {
    id("kx.publish") version "0.1.5" apply false
}

//val gitDescribe: String
//    get() = ByteArrayOutputStream().also {
//        rootProject.exec { commandLine("git", "describe", "--tags"); standardOutput = it; }
//    }.toString().trim().replace(Regex("-g([a-z0-9]+)$"), "-$1")

//println(gitDescribe)

subprojects {

    val platform = name.startsWith("platform")

    if(platform) {
        apply(plugin = "java-platform")
        apply(plugin = "maven-publish")
    }
    else
        apply(plugin = "kx.publish")
    //    apply(plugin = "org.gradle.kotlin.kotlin-dsl")

    repositories {
        mavenCentral()
        gradlePluginPortal()
        //        maven("https://repo.repsy.io/mvn/elect/kx/")
        maven("https://raw.githubusercontent.com/elect86/mary/master")
    }

    group = "kotlin.graphics.build-logic"
    version = "0.7.0+28"

    if (platform)
    // limited dsl support inside here
        extensions.configure<PublishingExtension>("publishing") {
            publications.create<MavenPublication>("maven") {
                from(components["javaPlatform"])
            }
            repositories.maven {
                url = uri("$rootDir/../mary")
            }
        }
}