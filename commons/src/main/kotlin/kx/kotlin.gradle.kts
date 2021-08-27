package kx

import org.jetbrains.kotlin.gradle.dsl.KotlinCommonProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm")
    //    id("org.jetbrains.kotlin.jvm")
    //    id("com.example.jacoco")
    id("com.github.johnrengelman.shadow")
}

repositories {
    mavenCentral()
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
}

group = "kotlin.graphics"

dependencies {

    val platformVersion = "0.3.2"

    implementation(platform("$group.platform:source:$platformVersion"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(platform("$group.platform:test:$platformVersion"))
    testImplementation("io.kotest:kotest-runner-junit5")
    testImplementation("io.kotest:kotest-assertions-core")
}

//val multiModule = subprojects.isNotEmpty()

//allprojects {
//
//    val isRootProject = this == rootProject
//
//    if (!multiModule || !isRootProject) {
//
//        apply(plugin = "kx.kotlin")

val SourceSet.compileKotlinTaskName: String
    get() = getCompileTaskName("kotlin")

val SourceSet.kotlin: SourceDirectorySet
//    get() = withConvention(KotlinSourceSet::class) { kotlin }
    get() = project.extensions.getByType<KotlinCommonProjectExtension>().sourceSets.getByName(name).kotlin

val jdk11 = sourceSets.main.get()

val jdk8 = sourceSets.create("jdk8") {
    java.setSrcDirs(emptySet<File>())
//    this.ex
    kotlin.setSrcDirs(emptySet<File>())
    java.setSrcDirs(jdk11.java.srcDirs)
    kotlin.setSrcDirs(jdk11.kotlin.srcDirs)
    java.setExcludes(listOf("module-info.java"))
    kotlin.setExcludes(listOf("module-info.java"))
}

java.registerFeature("jdk8") {
    usingSourceSet(jdk8)
    //    capability(project.group.toString(), project.name, project.version.toString())
    capability(group.toString(), name, version.toString())
    println(project)
    println(project.version)
    println("capability($group, $name, $version)")
}

configureCompileVersion(jdk8, 8)
configureCompileVersion(jdk11, 11)

val moduleName = "$group.$name"

fun configureCompileVersion(set: SourceSet, jdkVersion: Int) {
    kotlin {
        jvmToolchain {
            (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(jdkVersion))
        }
    }
    tasks {
        val target = if (jdkVersion == 8) "1.8" else jdkVersion.toString()
        named<KotlinCompile>(set.compileKotlinTaskName) {
            kotlinOptions {
                jvmTarget = target
                freeCompilerArgs += listOf("-Xinline-classes", "-Xopt-in=kotlin.RequiresOptIn")
            }
            source = sourceSets.main.get().kotlin
        }
        named<JavaCompile>(set.compileJavaTaskName) {
//            println("configureCompileVersion $set $jdkVersion")
            targetCompatibility = target
            sourceCompatibility = target
            modularity.inferModulePath.set(jdkVersion >= 9)
            javaCompiler.set(project.javaToolchains.compilerFor {
                languageVersion.set(JavaLanguageVersion.of(jdkVersion))
            }.get())
            source = set.allJava
            //            println(source.files)
            if (jdkVersion >= 9)
                options.compilerArgs = listOf("--patch-module", "$moduleName=${set.output.asPath}")
        }
        withType<Test> { useJUnitPlatform() }
    }
}


// == Add access to the 'modular' variant of kotlin("stdlib"): Put this into a buildSrc plugin and reuse it in all your subprojects
//configurations.all { attributes.attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 11) }

//        println("inferModulePath=${extensions.getByName<JavaPluginExtension>("java").modularity.inferModulePath.get()}")
//    }
//}


extensions.getByName<JavaPluginExtension>("java").withSourcesJar()