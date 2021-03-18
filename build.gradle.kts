import java.io.ByteArrayOutputStream

plugins {
    `kotlin-dsl` apply false
}

subprojects {

    group = "kotlin.graphics.build-logic"
    version = "0.7.0+30"

    val platform = name.startsWith("platform")

    if (platform)
        apply(plugin = "java-platform")
    else {
        apply(plugin = "org.gradle.kotlin.kotlin-dsl")
        apply(plugin = "java-library")
    }
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
        gradlePluginPortal()
        //        maven("https://repo.repsy.io/mvn/elect/kx/")
        maven("https://raw.githubusercontent.com/elect86/mary/master")
    }

    // limited dsl support inside here
    extensions.configure<PublishingExtension>("publishing") {
        if (platform)
            publications.create<MavenPublication>("maven") {
                from(components["javaPlatform"])
            }
        repositories.maven {
            url = uri("$rootDir/../mary")
        }
    }
}

val gitDescribe: String
    get() = ByteArrayOutputStream().also { exec { commandLine("git", "describe", "--tags"); standardOutput = it; } }
        .toString().trim().replace(Regex("-g([a-z0-9]+)$"), "-$1")

tasks {
    register("1)commit&push") {
        group = "kx"
        doLast {
            exec { commandLine("git", "add", ".") }
            var message = gitDescribe.substringBeforeLast('-')
            val commits = message.substringAfterLast('-').toInt() + 1
            message = message.substringBeforeLast('-') + "+$commits"
            exec { commandLine("git", "commit", "-m", message) }
            exec { commandLine("git", "push") }
        }
    }
    register("2)publish") {
        group = "kx"
        //        dependsOn("commit&push") not reliable
        finalizedBy(getTasksByName("publish", true))
    }
    register("3)commit&pushMary") {
        group = "kx"
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