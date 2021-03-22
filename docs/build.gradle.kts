
import org.gradle.kotlin.dsl.*

dependencies {

    val platformVersion = rootProject.extra["platformVersion"]
    "implementation"(platform("kotlin.graphics.platform:plugin:$platformVersion"))

    "implementation"("org.jetbrains.dokka:dokka-gradle-plugin")
    "implementation"("org.jetbrains.dokka:dokka-core")
}