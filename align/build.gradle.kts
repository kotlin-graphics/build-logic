plugins {
    id("my-plugin")
}

version = "0.0.5"

dependencies {

    val platformVersion = "0.3.5"
    implementation(platform("kotlin.graphics:platform-plugin:$platformVersion"))

    //    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    //    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin")
    //    implementation("com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin")

    //    implementation("kotlin.graphics.build-logic:dev:0.0.1")
    //    implementation("elect86.magik:elect86.magik.gradle.plugin")
}

gradlePlugin {
    // Define the plugin
    plugins.named("align") {
        id = "io.github.kotlin-graphics.align"
        displayName = "Dynamic alignment for importing kx projects"
        description = "This plugins performs a dynamic alignment based on the current version inside the platform lockfile"
    }
}

pluginBundle {
    website = "https://github.com/kotlin-graphics/build-logic"
    vcsUrl = "https://github.com/kotlin-graphics/build-logic"
    tags = listOf("github", "kotlin-graphics", "kx", "graphics", "3d-realtime-graphics", "kotlin", "platform", "dynamic", "alignment")
}