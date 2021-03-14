import java.io.ByteArrayOutputStream


plugins {
    id("kx.publish") version "0.0.9"
}

//val gitDescribe: String
//    get() = ByteArrayOutputStream().also {
//        rootProject.exec { commandLine("git", "describe", "--tags"); standardOutput = it; }
//    }.toString().trim().replace(Regex("-g([a-z0-9]+)$"), "-$1")

//println(gitDescribe)

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
    version = "0.7.0+25"
    println(version)

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