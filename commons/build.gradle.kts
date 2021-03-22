
dependencies {

    val platformVersion = rootProject.extra["platformVersion"] ?: "0.2.8+25"
    "implementation"(platform("kotlin.graphics.platform:plugin:$platformVersion"))

//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    "implementation"("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin")
    "implementation"("com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin")

    "implementation"(project(":kx"))
}