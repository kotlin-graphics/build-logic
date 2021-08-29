# build-logic

### Build-logic

The build logic has been extracted into dedicated conventional plugins (and [platforms](https://github.com/kotlin-graphics/platforms)), 
published on [a custom repository](https://github.com/kotlin-graphics/mary) to speed up development process.

### Structure

This costed me a lot in terms of developing time, but I hope it'll pay off in the long term..

The current project is structured as follows:
- commons:
    - kotlin: 
      - configure the base kotlin, compiles for jdk11 (main variant) and jdk8 variant
      - offers utils for easier the import of kotlin-graphics projects and lwjgl modules
    - docs, for docs auto generation by dokka
    - publish, relies on `magik`, apply `maven-publish`, set the publication and the mary repo

### Install

`settings.gradle.kts`

```kotlin
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
    }
}
```

`build.gradle.kts`

```kotlin
plugins {
    val build = "x.x.x"
    id("kx.kotlin") version build
    id("kx.dokka") version build
    id("kx.publish") version build
}
```