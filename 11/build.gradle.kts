
dependencies {

    val platformVersion = "0.3.3+3"
    implementation(platform("kotlin.graphics.platform:plugin:$platformVersion"))

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")

    implementation(project(":commons"))
}