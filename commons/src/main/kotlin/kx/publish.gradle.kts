package kx

import gradle.kotlin.dsl.accessors._e98ba513b34f86980a981ef4cafb3d49.publishing
import org.gradle.kotlin.dsl.`maven-publish`
import java.io.ByteArrayOutputStream

plugins {
    `maven-publish`
}

// limited dsl support inside here
publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
        suppressPomMetadataWarningsFor("runtimeElements")
    }
    repositories {
        maven {
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
//        finalizedBy(getTasksByName("publish", true))
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