
dependencies {

    val platformVersion = rootProject.extra["platformVersion"]
    "implementation"(platform("kotlin.graphics.platform:plugin:$platformVersion"))
}