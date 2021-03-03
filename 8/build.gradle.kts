
plugins {
    java
    `kotlin-dsl`
}

dependencies {

    implementation(platform("com.github.elect86.platforms:plugin:fe09de54"))

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")

    implementation(project(":commons"))
}