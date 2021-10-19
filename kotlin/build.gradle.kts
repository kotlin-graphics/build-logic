import magik.github

plugins {
    //        `kotlin-dsl`
    //    id("org.gradle.kotlin.kotlin-dsl")
    //    id("my-plugin")

    id("org.gradle.kotlin.kotlin-dsl")
    id("elect86.magik")
    `maven-publish`
    `java-library`

    //    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "0.14.0"
}

version = "0.0.1"//rootProject.version
group = rootProject.group


dependencies {

    val platformVersion = "0.3.3+24"
    implementation(platform("kotlin.graphics:platform-plugin:$platformVersion"))

    //    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    //    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin")
    //    implementation("com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin")

    //    implementation("kotlin.graphics.build-logic:dev:0.0.1")
    implementation("elect86.magik:elect86.magik.gradle.plugin")
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    github("kotlin-graphics/mary")
}

//magik { commitWithChanges.set(true) }

gradlePlugin {
    // Define the plugin
    plugins.named("io.github.kotlin.graphics.kotlin") {
        id = "io.github.kotlin.graphics.kotlin"
        displayName = "Apply general kotlin configuration for kx projects"
        description = "This setups the main variant for jdk11 and the jdk8 variant and brings source and test platform dependencies"
    }
}

pluginBundle {
    website = "https://github.com/kotlin-graphics/build-logic"
    vcsUrl = "https://github.com/kotlin-graphics/build-logic"
    tags = listOf("github", "kotlin-graphics", "kx", "graphics", "3d-realtime-graphics", "kotlin", "variant", "setup")
}

publishing {
    publications {
        repositories {
            //            maven {
            //                name = "prova"
            //                url = uri("$rootDir/prova")
            //            }
            github { domain = "kotlin-graphics/mary" }
            mavenLocal()
        }
    }
}