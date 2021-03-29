package kx

plugins {
    id("kx.snapshot")
}

val multiModule = subprojects.isNotEmpty()

allprojects {

    val isRootProject = this == rootProject

    if (!multiModule || !isRootProject) {

        apply(plugin = "maven-publish")
        apply(plugin = "java")

        // limited dsl support inside here
        extensions.configure<PublishingExtension>("publishing") {
            publications.create<MavenPublication>("maven") {
                if (multiModule)
                    afterEvaluate {
                        artifactId = "${rootProject.name}-${project.name}"
                        version = rootProject.version
                    }
                // else default value is fine
                //      artifactid = project.name
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