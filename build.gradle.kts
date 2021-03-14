
plugins {
    id("kx.publish") version "0.0.5"
}

//repositories {
//    mavenCentral()
//}

subprojects {

    val platform = name.startsWith("platform")

    apply(plugin = when {
        platform -> "java-platform"
        else -> "java"
    })
    apply(plugin = "maven-publish")
    //    apply(plugin = "org.gradle.kotlin.kotlin-dsl")

    repositories {
        mavenCentral()
        gradlePluginPortal()
//        maven("https://repo.repsy.io/mvn/elect/kx/")
        maven("https://raw.githubusercontent.com/elect86/mary/master")
    }

    group = "kotlin.graphics.build-logic"
    version = "0.7.4"

    // limited dsl support inside here
    fun publishing(configure: Action<PublishingExtension>) = extensions.configure("publishing", configure)

    publishing {
        if (platform)
            publications.create<MavenPublication>("maven") {
                from(components["javaPlatform"])
            }
        repositories.maven {
            url = uri("$rootDir/../mary")
            //            url = uri("../mary")
        }
    }
}