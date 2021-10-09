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

These plugins are optional but they bring down much of the pain involved into adding the projects into your dependencies.

In order to use them, you need to include in `settings.gradle.kts` the [mary repository](https://github.com/kotlin-graphics/mary) by adding the following:

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
    val build = "0.7.3+51"
    id("kx.kotlin") version build
    id("kx.dokka") version build
    id("kx.publish") version build
    id("kx.dynamic-align") version build
    id("kx.util") version build
}
```

- #### kx.kotlin
    Provide all the basic settings for compiling the principal variant with jdk11 using the modular structure (Jigsaw) and adding a `jdk8` variant compiled with the corresponding jdk8
- ### dokka
    Inactive for the moment, I need to fix some other stuff first to make sure it plays nice
- ### publish
    Setup publication for mary
- ### dynamic-align
    Align kotlin-graphics projects dynamically based on lockfiles such as [this one](https://github.com/kotlin-graphics/mary/blob/master/kotlin/graphics/platform-source/0.3.3+24/platform-source-0.3.3+24-lockfile.txt). This is quite confortable, because in this way when I'm bumping up dependencies on each single project, I don't need to publish a new [`platform`](https://github.com/kotlin-graphics/platforms) each time. I just modify it manually (this can be also automatized though)
- ### util
    Provide some confortables accessors to set kotlin-graphics and lwjgl dependencies. Example:
```kotlin
    implementation(unsigned, kool, glm, gli, gln, uno.core)
    Lwjgl { implementation(jemalloc, glfw, opengl, remotery, stb) }
```

