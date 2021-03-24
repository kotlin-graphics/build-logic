import java.io.ByteArrayOutputStream

plugins {
    `kotlin-dsl` apply false
    id("kx.snapshot") version "0.0.3"
}

version = "0.7.0+69" // for ::bump
ext["platformVersion"] = "0.2.8+29"

subprojects {

    group = "kotlin.graphics.build-logic"
    version = when (name) {
        "dev" -> "0.0.3"
        else -> rootProject.version
    }

    apply(plugin = "org.gradle.kotlin.kotlin-dsl")
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
        gradlePluginPortal()
        //        maven("https://repo.repsy.io/mvn/elect/kx/")
        maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
    }

    // limited dsl support inside here
    extensions.configure<PublishingExtension>("publishing") {
        repositories.maven {
            url = uri("$rootDir/../mary")
        }
    }
}

//val gitDescribe: String
//    get() = ByteArrayOutputStream().also { exec { commandLine("git", "describe", "--tags"); standardOutput = it; } }.toString().trim()
//
//val gitDistance: Int
//    get() = gitDescribe.substringBeforeLast("-g").substringAfterLast('-').toInt() + 1 // the next is the one we are interested in
//
//val gitTag: String
//    get() = gitDescribe.substringBeforeLast('-').substringBeforeLast('-')
//
//fun gitAddCommitPush(message: String, dir: File = rootDir) {
//    exec { workingDir = dir; commandLine("git", "add", "."); }
//    exec { workingDir = dir; commandLine("git", "commit", "-m", message); }
//    exec { workingDir = dir; commandLine("git", "push"); }
//}
//
//tasks {
//    register("1)bump,commit,push") {
//        group = "kx-dev"
//        doLast {
//            bump()
//            gitAddCommitPush("$gitTag+$gitDistance")
//        }
//    }
//    register("2)publish") {
//        group = "kx-dev"
//        //        dependsOn("commit&push") not reliable
//        finalizedBy(getTasksByName("publish", true) - named(":dev:publish"))
//    }
//    register("3)[mary]commit,push") {
//        group = "kx-dev"
//        doLast {
//            gitAddCommitPush("""
//                |$project :arrow_up:
//                |snapshot $gitDescribe""".trimMargin(), file("../mary"))
//        }
//        //        mustRunAfter("publishSnapshot") // order
//    }
//}
//
//fun bump() {
//    val text = buildFile.readText()
//    val version = version.toString()
//    val bump = "${version.split('+').first()}+%02d".format(gitDistance)
//    buildFile.writeText(text.replace(version, bump))
//}