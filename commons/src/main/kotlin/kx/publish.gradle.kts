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
                if(multiModule) {
                    artifactId = "${rootProject.name}-${project.name}"
                    // this plugin gets applied before anything else, so `version` in the subprojects is still `undefined`
                    version = rootProject.version.toString()
                }
                // else default values are fine
                //      artifactid = project.name
                //      version = project.version
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