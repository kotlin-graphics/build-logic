package kx

val multiModule = subprojects.isNotEmpty()

allprojects {

    val isRootProject = this == rootProject

    if (!multiModule || !isRootProject) {

        apply(plugin = "java-library")

        repositories {
            maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
        }

        dependencies {

            val platformVersion = "0.3.3+16"
            "implementation"(platform("kotlin.graphics.platform:source:$platformVersion"))
        }
    }
}


