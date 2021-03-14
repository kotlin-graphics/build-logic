
plugins {
    `kotlin-dsl`
}

dependencies {

    implementation(platform(project(":platform-plugin")))

    implementation("org.jetbrains.dokka:dokka-gradle-plugin")
    implementation("org.jetbrains.dokka:dokka-core")
}