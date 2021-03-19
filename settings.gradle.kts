
//dependencyResolutionManagement {
//    repositories {
//        gradlePluginPortal()
////        google()
//    }
//}

pluginManagement {
    repositories {
//        mavenLocal()
        gradlePluginPortal()
//        maven("https://repo.repsy.io/mvn/elect/kx/")
        maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
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
include("kx")
include("platform-plugin")
include("platform-source")
include("platform-test")
