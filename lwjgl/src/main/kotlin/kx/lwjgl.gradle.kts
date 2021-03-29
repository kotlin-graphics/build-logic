package kx

val multiModule = subprojects.isNotEmpty()

allprojects {

    val isRootProject = this == rootProject

    if (!multiModule || !isRootProject) {

        repositories {
            maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
        }

        dependencies {

            val platformVersion = when {
                project.hasProperty("platformVersion") -> project.property("platformVersion")
                else -> "0.2.8+42"
            }
            implementation(platform("kotlin.graphics.platform:source:$platformVersion"))
        }
    }
}


