
dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}

rootProject.name = "build-logic"

//includeBuild("/home/elect/IdeaProjects/platforms")
include("commons")
include("8")
include("11")
include("docs")
include("lwjgl")