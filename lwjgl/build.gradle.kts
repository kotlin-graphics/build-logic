
dependencies {

    val platformVersion = rootProject.extra["platformVersion"] ?: "0.2.8+25"
    "implementation"(platform("kotlin.graphics.platform:plugin:$platformVersion"))
}