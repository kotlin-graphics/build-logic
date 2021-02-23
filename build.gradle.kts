plugins {
    `kotlin-dsl`
    `maven-publish`
    `java-gradle-plugin`
}

group = "com.github.kotlin_graphics"
version = "0.0.1"

repositories.mavenCentral()

publishing {
    publications.create<MavenPublication>("mavenJava") {
        from(components["java"])
    }
}

gradlePlugin {
    // Define the plugin
    plugins.creating {
        id = "kx.kotlin"
        implementationClass = "upgraded.octo.guacamole.UpgradedOctoGuacamolePlugin"
    }
}