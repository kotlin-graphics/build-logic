
dependencies {

    val platformVersion = "0.3.3+16"
    implementation(platform("kotlin.graphics.platform:plugin:$platformVersion"))

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")

    implementation(project(":commons"))
}