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

### Plugins

In order to use kotlin-graphics plugins (they are option), in `settings.gradle.kts`
you need to include the [mary repository](https://github.com/kotlin-graphics/mary) by adding the following:

```kotlin
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
    }
}
```

And then Gradle will now be able to locate them when you want to apply them in `build.gradle.kts` by:

```kotlin
plugins {
    val build = ".."
    id("kx.kotlin") version build
    id("kx.dokka") version build
    id("kx.publish") version build
}
```