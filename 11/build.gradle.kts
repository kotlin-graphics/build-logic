
dependencies {

    implementation(platform("com.github.elect86.platforms:plugin:fe09de54"))

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")

    implementation(project(":commons"))
}

//gradlePlugin {
//    // Define the plugin
//    plugins.create("11") {
//        id = "kx.kotlin.11"
//        implementationClass = "kx.kotlin.11"
//    }
//}