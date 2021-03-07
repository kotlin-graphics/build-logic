
plugins {
    `kotlin-dsl`
}

dependencies {

    implementation(platform("kx.platform:plugin:0.1.7"))

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")

    implementation(project(":commons"))
}