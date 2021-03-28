package kx

plugins {
    id("kx.snapshot")
}

subprojects {

    apply(plugin = "maven-publish")

    // limited dsl support inside here
    extensions.configure<PublishingExtension>("publishing") {
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
}