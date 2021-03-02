
subprojects {

    apply(plugin = "java")
    apply(plugin = "maven-publish")
//    apply(plugin = "java-gradle-plugin")

    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io")
    }

    group = "kx.build-logic"
    version = "0.1.0"

    // limited dsl support inside here
    fun Project.publishing(configure: Action<PublishingExtension>): Unit =
        (this as ExtensionAware).extensions.configure("publishing", configure)

    publishing {
        publications.create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}