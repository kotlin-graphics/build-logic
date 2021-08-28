
import org.gradle.kotlin.dsl.*

dependencies {

    val platformVersion = "0.3.3+13"
    "implementation"(platform("kotlin.graphics.platform:plugin:$platformVersion"))

    "implementation"("org.jetbrains.dokka:dokka-gradle-plugin")
    "implementation"("org.jetbrains.dokka:dokka-core")
}