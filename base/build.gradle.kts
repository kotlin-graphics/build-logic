//import magik.github

plugins {
    id("my-plugin")
}

version = "0.0.10"

dependencies {

    val platformVersion = "0.3.6"
    implementation(platform("kotlin.graphics:platform-plugin:$platformVersion"))

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    //    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin")
    //    implementation("com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin")
}

//magik { commitWithChanges.set(true) }

gradlePlugin {
    // Define the plugin
    plugins.named("base") {
        id = "io.github.kotlin-graphics.base"
        displayName = "Apply general kotlin configuration for kx projects"
        description = "This setups the main variant for jdk11 and the jdk8 variant and brings source and test platform dependencies"
    }
}

pluginBundle {
    website = "https://github.com/kotlin-graphics/build-logic"
    vcsUrl = "https://github.com/kotlin-graphics/build-logic"
    tags = listOf("github", "kotlin-graphics", "kx", "graphics", "3d-realtime-graphics", "kotlin", "variant", "setup")
}