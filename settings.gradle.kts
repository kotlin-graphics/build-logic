
dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if(requested.id.id == "docs")
                useModule("com.github.elect86:docs:9c008a8b")//.also { println("found") }
//            if(requested.id.id == "kx.kotlin")
//            //                useModule("com.github.elect86.build-logic:11:4179b13f")
//                useModule("kx.build-logic:commons:${requested.version}")
        }
    }
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven("https://jitpack.io")
        maven("https://repo.repsy.io/mvn/elect/kx/")
    }
}

rootProject.name = "build-logic"

//includeBuild("/home/elect/IdeaProjects/platforms")
include("commons")
include("8")
include("11")
include("docs")
include("lwjgl")
include("jitpack")