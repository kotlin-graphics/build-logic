package kx

import magik.github

plugins {
    id("elect86.magik")
}

val multiModule = subprojects.isNotEmpty()

allprojects {

    val isRootProject = this == rootProject

//    println("$this, multimodule=$multiModule, isRootProject=$isRootProject")

    if (!multiModule || !isRootProject) {

        apply(plugin = "maven-publish")
        apply(plugin = "java")
        apply(plugin = "elect86.magik")

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
//                maven {
//                    name = "repo"
//                    url = uri("$rootDir/repo")
//                }
                github {
                    domain = "kotlin-graphics/mary"
                }
            }
        }
    }
}