import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
}

publishing {
    publications.create<MavenPublication>("mavenJava") {
        from(components["java"])
    }
}