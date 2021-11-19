plugins {
    id("my-plugin")
}

version = "0.0.2"

gradlePlugin {
    // Define the plugin
    plugins.named("utils") {
        id = "io.github.kotlin-graphics.utils"
        displayName = "Import additional configuration accessors to easier kx importing"
        description = "This setups brings into the classpath additional wrapper to import kx project conveniently"
    }
}

pluginBundle {
    website = "https://github.com/kotlin-graphics/build-logic"
    vcsUrl = "https://github.com/kotlin-graphics/build-logic"
    tags = listOf("github", "kotlin-graphics", "kx", "graphics", "3d-realtime-graphics", "kotlin", "utils", "implementation", "api")
}