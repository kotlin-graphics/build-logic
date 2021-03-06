
plugins {
    `kotlin-dsl`
}

dependencies {

    implementation(platform("kx.platform:plugin:0.1.4"))

//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin")
    implementation("com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin")

    implementation(project(":impl"))
}