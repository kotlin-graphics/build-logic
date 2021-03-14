subprojects {

    apply(
        plugin = when {
            name.startsWith("platform") -> "java-platform"
            else -> "java"
        }
    )
    apply(plugin = "maven-publish")
    //    apply(plugin = "org.gradle.kotlin.kotlin-dsl")

    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://repo.repsy.io/mvn/elect/kx/")
        maven("https://raw.githubusercontent.com/elect86/mary/master")
    }

    group = "kotlin.graphics.build-logic"
    version = "0.7.0"

    // limited dsl support inside here
    fun publishing(configure: Action<PublishingExtension>) = extensions.configure("publishing", configure)

    publishing {
        repositories.maven {
            url = uri("https://raw.githubusercontent.com/elect86/mary/master")
            //            name = "repsy"
            //            url = uri("https://repo.repsy.io/mvn/elect/kx")
            //            credentials(PasswordCredentials::class)
        }
    }
}

val gitDescribe: String
    get() {
        val stdout = java.io.ByteArrayOutputStream()
        rootProject.exec {
            commandLine("git", "describe", "--tags")
            standardOutput = stdout
        }
        return stdout.toString().trim().replace(Regex("-g([a-z0-9]+)$"), "-$1")
    }

tasks {
    register("commit&push") {
        group = "kx"
        doLast {
            rootProject.exec { commandLine("git", "add", ".") }
            var message = gitDescribe.substringBeforeLast('-')
            val commits = message.substringAfterLast('-').toInt() + 1
            message = message.substringBeforeLast('-') + "-$commits"
            rootProject.exec { commandLine("git", "commit", "-m", message) }
            rootProject.exec { commandLine("git", "push") }
        }
    }
    register("publishSnapshot") {
        group = "kx"
        doLast {
            subprojects { version = gitDescribe }
            println("publish")
        }
        dependsOn("commit&push")
        finalizedBy(
            subprojects.map { it.tasks.matching { task -> task.name == "publish" } },
            "commit&pushMary"
        )
    }
    register("commit&pushMary") {
        group = "kx"
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
    }
}