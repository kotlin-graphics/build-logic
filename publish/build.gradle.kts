plugins {
    id("my-plugin")
}

dependencies {

    val platformVersion = "0.3.3+24"
    implementation(platform("kotlin.graphics:platform-plugin:$platformVersion"))

    //    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
//    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin")

    //    implementation("kotlin.graphics.build-logic:dev:0.0.1")
    implementation("elect86.magik:elect86.magik.gradle.plugin")
}

version = "0.0.1"

gradlePlugin {
    // Define the plugin
    plugins.named("publish") {
        id = "io.github.kotlin-graphics.publish"
        displayName = "Apply general kotlin configuration for publishing for kx projects"
        description = "This apply `maven-publish`, magik plugins and setup publishing"
    }
}

pluginBundle {
    website = "https://github.com/kotlin-graphics/build-logic"
    vcsUrl = "https://github.com/kotlin-graphics/build-logic"
    tags = listOf("github", "kotlin-graphics", "kx", "graphics", "3d-realtime-graphics", "kotlin", "publishing", "mary")
}