import java.io.ByteArrayOutputStream

plugins {
    `kotlin-dsl` apply false
}

version = "0.7.0+63" // for ::bump
ext["platformVersion"] = "0.2.8+29"

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

object git {

//    val describe: String
//        get() {
//            val out = ByteArrayOutputStream()
//            exec {
//                commandLine("git", "describe", "--tags")
//                standardOutput = out
//            }
//            return out.toString().trim()
//        }
//
//    val distance: Int
//        get() = describe.substringBeforeLast("-g").substringAfterLast('-').toInt()
//
//    val tag: String
//        get() = describe.substringBeforeLast('-').substringBeforeLast('-')

    fun addCommitPush(message: String, dir: File = rootDir) {
//        exec { workingDir = dir; commandLine("git", "add", "."); }
//        exec { workingDir = dir; commandLine("git", "commit", "-m", message); }
//        exec { workingDir = dir; commandLine("git", "push"); }
    }
}

tasks {
//    register("bump") { bump() }
//    register("gitDescribe") { println(git.describe) }
//    register("gitDistance") { println(git.distance) }
//    register("1)bump,commit,push") {
//        group = "kx-dev"
//        doLast {
//            bump()
//            git.addCommitPush("${git.tag}+${git.distance}")
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
//            git.addCommitPush("""
//                    |$project :arrow_up:
//                    |snapshot ${git.describe}""".trimMargin(), file("../mary"))
//        }
//        //        mustRunAfter("publishSnapshot") // order
//    }
}

//fun bump() {
//    val text = buildFile.readText()
//    val version = version.toString()
//    val bump = "${version.split('+').first()}+%02d".format(git.distance + 1)
//    buildFile.writeText(text.replace(version, bump))
//}