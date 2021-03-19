package kx

import java.io.ByteArrayOutputStream

// limited dsl support inside here
extensions.configure<PublishingExtension>("publishing")  {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
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
    }.toString().trim().replace(Regex("-g([a-z0-9]+)$"), "-$1")

tasks {
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
    val ofs = text.indexOf("version")
    val start = text.indexOf('"', startIndex = ofs) + 1
    val end = text.indexOf('"', startIndex = start)
    val version = text.substring(start, end)
    val plus = version.indexOf('+')
    buildFile.writeText(text.replace(version, when {
        plus != -1 -> version.split('+').let { "${it[0]}+%02d".format(it[1].toInt() + 1) }
        else -> "$version+01"
    }))
}