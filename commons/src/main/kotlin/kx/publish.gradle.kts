package kx

plugins {
    id("kx.snapshot")
}

val multiModule = subprojects.isNotEmpty()

allprojects {

    val isRootProject = this == rootProject

    if (!multiModule || !isRootProject) {

        println("$this, $version, ${rootProject.version}")
        if (multiModule) {
            println("in")
            version = rootProject.version
        }

        apply(plugin = "maven-publish")
        apply(plugin = "java")

        // limited dsl support inside here
        extensions.configure<PublishingExtension>("publishing") {
            publications.create<MavenPublication>("maven") {
                if(multiModule)
                    artifactId = "${rootProject.name}-${project.name}"
                // else default -> project.name
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
}