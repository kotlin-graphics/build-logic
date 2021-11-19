
dependencies {

    val platformVersion = "0.3.4"
    implementation(platform("kotlin.graphics:platform-plugin:$platformVersion"))

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")

    implementation(project(":commons"))
}