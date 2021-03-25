# build-logic

### Build-logic

The build logic has been extracted into dedicated conventional plugins (and [platforms](https://github.com/kotlin-graphics/platforms)), 
published on [a custom repository](https://github.com/kotlin-graphics/mary) to speed up development process.

### Structure

This costed me a lot in terms of developing time, but I hope it'll pay off in the long term..

The current project is structured as follows:
- kx, actually the only dummy conventional plugin, provides helpers for kx imports, such as `kxImplementation(..)` 
- dev, used for snapshots. Used recursively by the same project.. how? Relying on a previous version :)
- commons:
    - kotlin, configure the base kotlin, applying the plugin and importing the jdk
    - publish, relies on `dev`, apply `maven-publish`, set the publication and the mary repo
- 8 and 11, simply finish to configure for jdk8 and jdk11 with JPMS
- docs for dokka
- lwjgl, similar to kx but for lwjgl with `lwjglImplementation(..)`
