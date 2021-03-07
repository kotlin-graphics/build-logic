
plugins {
    `kotlin-dsl`
}

dependencies {

    implementation(platform("kx.platform:plugin:0.1.7"))

    implementation("org.jetbrains.dokka:dokka-gradle-plugin")
    implementation("org.jetbrains.dokka:dokka-core")
}