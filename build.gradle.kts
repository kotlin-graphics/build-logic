
subprojects {

    apply(plugin = "java")
    apply(plugin = "maven-publish")
//    apply(plugin = "org.gradle.kotlin.kotlin-dsl")

    repositories {
        maven("https://jitpack.io")
        mavenCentral()
        gradlePluginPortal()
    }

//    group = "kx.build-logic"
    group = "com.github.elect86.build-logic"
    version = "0.1.6"

    // limited dsl support inside here
    fun Project.publishing(configure: Action<PublishingExtension>): Unit =
        (this as ExtensionAware).extensions.configure("publishing", configure)

    publishing {
        publications.create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}