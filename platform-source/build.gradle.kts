
// allow the definition of dependencies to other platforms like the Spring Boot BOM
extensions.getByName<JavaPlatformExtension>("javaPlatform").allowDependencies()

dependencies {

    "api"(platform("org.lwjgl:lwjgl-bom:3.2.3"))

    constraints {
//        api("org.apache.juneau:juneau-marshall:8.2.0")
//        api(platform(":kx"))

        val kx = "kotlin.graphics"
//        listOf()

        "api"("$kx:unsigned:51d8dfea")
        "api"("$kx:kool:95bcdc4d")
        "api"("$kx:glm:8dae70c1")
        "api"("$kx:gli:12cd3342")
        "api"("$kx:gln:9eae85c8")
        "api"("$kx:vkk:9d1cd859")
    }
}
