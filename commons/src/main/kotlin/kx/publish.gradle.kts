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

val gitDescribe: String
    get() = ByteArrayOutputStream().also {
        rootProject.exec {
            commandLine("git", "describe", "--tags")
            standardOutput = it
        }
    }.toString().trim().replace(Regex("-([a-z0-9]+)-g([a-z0-9]+)$"), "+$1-$2")

val gitDistance: Int
    get() = ByteArrayOutputStream().also {
        rootProject.exec {
            commandLine("git", "describe", "--tags")
            standardOutput = it
        }
    }.toString().trim().substringBeforeLast("-g").substringAfterLast('-').toInt()

tasks {
    register("gitDescribe") { println(gitDescribe) }
    register("gitDistance") { println(gitDistance) }
    register("1)bump,commit,push") {
        group = "kx-dev"
        doLast {
            bump()
            rootProject.exec { commandLine("git", "add", ".") }
            var message = gitDescribe.substringBeforeLast('-')
            val commits = message.substringAfterLast('-').toInt() + 1
            message = message.substringBeforeLast('-') + "+$commits"
            rootProject.exec { commandLine("git", "commit", "-m", message) }
            rootProject.exec { commandLine("git", "push") }
        }
    }
    register("2)publish") {
        group = "kx-dev"
        //        dependsOn("commit&push") not reliable
        finalizedBy(getTasksByName("publish", true))
    }
    register("3)[mary]commit,push") {
        group = "kx-dev"
        doLast {
            val maryDir = file("$rootDir/../mary")
            rootProject.exec {
                workingDir = maryDir
                commandLine("git", "add", ".")
            }
            val message = """
                |$project :arrow_up:
                |snapshot $gitDescribe
            """.trimMargin()
            rootProject.exec {
                workingDir = maryDir
                commandLine("git", "commit", "-m", message)
            }
            rootProject.exec {
                workingDir = maryDir
                commandLine("git", "push")
            }
        }
        //        mustRunAfter("publishSnapshot") // order
    }
}

fun bump() {
    val text = buildFile.readText()
    val version = version.toString()
    val plus = version.indexOf('+')
    buildFile.writeText(text.replace(version, when {
        plus != -1 -> {
            val (tag, delta) = version.split('+')
            "$tag+%02d".format(delta.toInt() + 1)
        }
        else -> "$version+01"
    }))
}