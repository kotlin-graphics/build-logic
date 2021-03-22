
dependencies {

    val platformVersion = rootProject.extra["platformVersion"]
    implementation(platform("kotlin.graphics.platform:plugin:$platformVersion"))

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")

    implementation(project(":commons"))
}