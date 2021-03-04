
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
    version = "0.2.9"

    // limited dsl support inside here
    fun Project.publishing(configure: Action<PublishingExtension>): Unit =
        (this as ExtensionAware).extensions.configure("publishing", configure)

    publishing {
        publications.create<MavenPublication>("maven") {
            from(components["java"])
        }
        repositories.maven {
            name = "repsy"
            url = uri("https://repo.repsy.io/mvn/elect/kx")
            credentials(PasswordCredentials::class)
        }
    }
}