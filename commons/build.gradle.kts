plugins {
//    `kotlin-dsl`
//    id("org.gradle.kotlin.kotlin-dsl")
    id("my-plugin")
}

dependencies {

    val platformVersion = "0.3.3+22"
    implementation(platform("kotlin.graphics.platform:plugin:$platformVersion"))

    //    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
//    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin")
    implementation("com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin")

    //    implementation("kotlin.graphics.build-logic:dev:0.0.1")
    implementation("elect86.magik:elect86.magik.gradle.plugin")
}