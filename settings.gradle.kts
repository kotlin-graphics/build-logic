pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
    }
}

rootProject.name = "build-logic"

gradle.rootProject {
    group = "io.github.kotlin-graphics"
}

include("utils", "base", "align", "publish")
//include("docs")