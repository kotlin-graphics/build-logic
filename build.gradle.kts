
subprojects {

    apply(plugin = "java")
    apply(plugin = "maven-publish")
    //    apply(plugin = "org.gradle.kotlin.kotlin-dsl")

    repositories {
        maven("https://jitpack.io")
        mavenCentral()
        gradlePluginPortal()
        maven("https://repo.repsy.io/mvn/elect/kx/")
    }

    group = "kx.build-logic"
    version = "0.4.0"

    // limited dsl support inside here
    fun publishing(configure: Action<PublishingExtension>) = extensions.configure("publishing", configure)

    publishing {
        repositories.maven {
            name = "repsy"
            url = uri("https://repo.repsy.io/mvn/elect/kx")
            credentials(PasswordCredentials::class)
        }
    }
}