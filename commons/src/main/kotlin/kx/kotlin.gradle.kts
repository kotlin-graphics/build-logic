package kx

import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
}

repositories {
    mavenCentral()
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
}

version = rootProject.version
group = rootProject.group

dependencies {

    val platformVersion = "0.3.3+22"

    implementation(platform("kotlin.graphics.platform:source:$platformVersion"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(platform("kotlin.graphics.platform:test:$platformVersion"))
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
    get() = project.extensions.getByType<KotlinJvmProjectExtension>().sourceSets.getByName(name).kotlin

val jdk11 = sourceSets.main.get()

val jdk8 = sourceSets.create("jdk8") {
    java.setSrcDirs(emptySet<File>())
    kotlin.setSrcDirs(emptySet<File>())
    java.setSrcDirs(jdk11.java.srcDirs)
    kotlin.setSrcDirs(jdk11.kotlin.srcDirs)
    java.setExcludes(listOf("module-info.java"))
    kotlin.setExcludes(listOf("module-info.java"))
}

java.registerFeature("jdk8") {
    usingSourceSet(jdk8)
    capability(group.toString(), name, version.toString())
}

val moduleName = "$group.$name"

configureCompileVersion(jdk8, 8)
configureCompileVersion(jdk11, 11)

fun configureCompileVersion(set: SourceSet, jdkVersion: Int) {
    tasks {
        val target = if (jdkVersion == 8) "1.8" else jdkVersion.toString()
        val compiler = project.javaToolchains.compilerFor {
            languageVersion.set(JavaLanguageVersion.of(jdkVersion))
        }.get()
        named<KotlinCompile>(set.compileKotlinTaskName) {
            targetCompatibility = target
            sourceCompatibility = target
//            println("$name, ${set.compileKotlinTaskName}, $target")
            kotlinOptions {
                jdkHome = compiler.metadata.installationPath.asFile.absolutePath
//                println(jdkHome)
                jvmTarget = target
                freeCompilerArgs += listOf("-Xinline-classes", "-Xopt-in=kotlin.RequiresOptIn")
//                classpath = files()
//                println(classpath.files)
            }
            source = set.kotlin
//            println(source.files)
        }
        named<JavaCompile>(set.compileJavaTaskName) {
//            println("$name, ${set.compileJavaTaskName}, $target")
            targetCompatibility = target
            sourceCompatibility = target
            modularity.inferModulePath.set(jdkVersion >= 9)
//            println("modular: ${modularity.inferModulePath.get()}")
            javaCompiler.set(compiler)
            source = set.allJava
//            println(source.files)
//            println(moduleName)
            if (jdkVersion >= 9) {
                options.compilerArgs = listOf("--patch-module", "$moduleName=${set.output.asPath}")
//                println(set.output.asPath)
            }
//            println(classpath.files)
        }
        withType<Test> { useJUnitPlatform() }
    }
}

// configure compileTestKotlin
tasks.compileTestKotlin {
    targetCompatibility = "11"
    sourceCompatibility = "11"
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs += listOf("-Xinline-classes", "-Xopt-in=kotlin.RequiresOptIn")
    }
    source = sourceSets.test.get().allSource
    //        println(source.files)
}

configurations {
    named("jdk8Implementation") {
        extendsFrom(implementation.get())
    }
}

// == Add access to the 'modular' variant of kotlin("stdlib"): Put this into a buildSrc plugin and reuse it in all your subprojects
//configurations.all { attributes.attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 11) }

//        println("inferModulePath=${extensions.getByName<JavaPluginExtension>("java").modularity.inferModulePath.get()}")
//    }
//}

java { withSourcesJar() }