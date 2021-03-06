
dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven("https://jitpack.io")
//        maven("https://repo.repsy.io/mvn/elect/kx/")
    }
}

rootProject.name = "build-logic"

//includeBuild("/home/elect/IdeaProjects/platforms")
include("commons")
include("8")
include("11")
include("docs")
include("lwjgl")
//include("jitpack")
include("impl")
