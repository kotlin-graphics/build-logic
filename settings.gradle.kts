
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
    }
}

rootProject.name = "build-logic"

gradle.rootProject {
    group = "kotlin.graphics.build-logic"
    version = "0.7.3+11"
}

include("commons")
include("utils")
//include("docs")

//includeBuild("../magik")