import java.io.ByteArrayOutputStream

plugins {
    `kotlin-dsl` apply false
}

version = "0.7.0+60" // for ::bump
ext["platformVersion"] = "0.2.8+25"

subprojects {

    group = "kotlin.graphics.build-logic"
    version = rootProject.version

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

val gitDescribe: String
    get() = ByteArrayOutputStream().also { exec { commandLine("git", "describe", "--tags"); standardOutput = it; } }
        .toString().trim().replace(Regex("-g([a-z0-9]+)$"), "-$1")

tasks {
    register("1)bump,commit,push") {
        group = "kx-dev"
        doLast {
            bump()
            exec { commandLine("git", "add", ".") }
            var message = gitDescribe.substringBeforeLast('-')
            val commits = message.substringAfterLast('-').toInt() + 1
            message = message.substringBeforeLast('-') + "+$commits"
            exec { commandLine("git", "commit", "-m", message) }
            exec { commandLine("git", "push") }
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
            val maryDir = file("../mary")
            exec {
                workingDir = maryDir
                commandLine("git", "add", ".")
            }
            val message = """
                |$project :arrow_up:
                |snapshot $gitDescribe
            """.trimMargin()
            exec {
                workingDir = maryDir
                commandLine("git", "commit", "-m", message)
            }
            exec {
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