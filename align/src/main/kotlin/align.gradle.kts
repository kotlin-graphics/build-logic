import java.net.URL

plugins {
    `java-library`
}

//abstract class DynamicAlignExtension {
//
//    @get:Input
//    abstract val verbose: Property<Boolean>
//
//    init {
//        verbose.convention(false)
//    }
//}
//
//val settings = project.extensions.create<DynamicAlignExtension>("dynamic-align")
//
//val lockfile: String = configurations.run {
//    implementation.get().run {
//        dependencies.first { it.group == "kotlin.graphics.platform" && it.name == "source" }.run {
//            val domain = "https://raw.githubusercontent.com/kotlin-graphics/mary/master"
//            // kotlin/graphics/platform/source/x.x.x/source-x.x.x-lockfile.txt
//            val file = "${group!!.split('.').joinToString("/")}/$name/${version!!}/$name-${version!!}-lockfile.txt"
//            URL("$domain/$file")
//        }.readText()
//    }
//}
//
//dependencies {
//    //    implementation(platform("kotlin.graphics.platform:source:0.3.3+18"))
//    //    implementation(group = "kotlin.graphics.platform" , name = "source", version = "0.3.3+18", classifier = "lockfile", ext = "txt")
//    //    implementation("kotlin.graphics.platform:source:0.3.3+18:lockfile@txt")
//
//    val lines = lockfile.split('\n').filter { !it.startsWith('#') }
//    project.dependencies {
//        constraints {
//            for (line in lines) {
//                if(settings.verbose.get())
//                    println("api($line)")
//                api(line)
//            }
//        }
//    }
//}

val verbose: Boolean
    get() = System.getProperty("alignVerbose").toBoolean()

dependencies {
    //    implementation(platform("kotlin.graphics.platform:source:0.3.3+18"))
    //    implementation(group = "kotlin.graphics.platform" , name = "source", version = "0.3.3+18", classifier = "lockfile", ext = "txt")
    //    implementation("kotlin.graphics.platform:source:0.3.3+18:lockfile@txt")

    val lockfile: String = run {
        val domain = "https://raw.githubusercontent.com/kotlin-graphics/mary/master"
        val platformVersion = "0.3.6"
        val file = "kotlin/graphics/platform-source/$platformVersion/platform-source-$platformVersion-lockfile.txt"
        if (verbose) println("$domain/$file")
        URL("$domain/$file").readText()
    }

    if (verbose) println(lockfile)

    val lines = lockfile.split('\n').filter { !it.startsWith('#') && it.isNotBlank() }
    project.dependencies.constraints {
        for (line in lines) {
            if (verbose) println("api($line)")
            add("api", line)
        }
    }
}