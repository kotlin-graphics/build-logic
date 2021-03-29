package kx

plugins {
    id("kx.snapshot")
}

subprojects {

    apply(plugin = "maven-publish")
    apply(plugin = "java")

    // limited dsl support inside here
    extensions.configure<PublishingExtension>("publishing") {
        publications.create<MavenPublication>("maven") {
            artifactId = "${rootProject.name}-${project.name}"
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