package kx

import org.gradle.api.attributes.java.TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    //    `java-library`
    id("kx.kotlin") apply false
}

val Project.sourceSets: SourceSetContainer
    get() =
        (this as ExtensionAware).extensions.getByName("sourceSets") as SourceSetContainer

val SourceSetContainer.main: NamedDomainObjectProvider<SourceSet>
    get() = named<SourceSet>("main")


val multiModule = subprojects.isNotEmpty()

allprojects {

    val isRootProject = this == rootProject

    if (!multiModule || !isRootProject) {

        apply(plugin = "kx.kotlin")

        tasks {

            withType<KotlinCompile>().all {
                kotlinOptions.jvmTarget = "11"
                sourceCompatibility = "11"
            }

            // this is needed because we have a separate compile step in this example with the 'module-info.java' is in 'main/java' and the Kotlin code is in 'main/kotlin'
            val module = "$group.${"${if (multiModule) rootProject.name else ""}."}$name" // uno-
            named<JavaCompile>("compileJava") {
                options.compilerArgs = listOf("--patch-module", "$module=${sourceSets.main.get().output.asPath}")
            }
        }

        extensions.getByName<JavaPluginExtension>("java").modularity.inferModulePath.set(true)

        // == Add access to the 'modular' variant of kotlin("stdlib"): Put this into a buildSrc plugin and reuse it in all your subprojects
        configurations.all { attributes.attribute(TARGET_JVM_VERSION_ATTRIBUTE, 11) }
    }
}