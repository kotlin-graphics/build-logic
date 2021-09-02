package kx

import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import java.net.URL

abstract class dynamicAlignExtension {

    @get:Input
    abstract val verbose: Property<Boolean>

    init {
        verbose.convention(false)
    }
}

val settings = extensions.create<dynamicAlignExtension>("dynamic-align")

val lockfile: String = configurations.run {
    named<Configuration>("implementation").get().run {
        dependencies.first { it.group == "kotlin.graphics.platform" && it.name == "source" }.run {
            val domain = "https://raw.githubusercontent.com/kotlin-graphics/mary/master"
            // kotlin/graphics/platform/source/x.x.x/source-x.x.x-lockfile.txt
            val file = "${group!!.split('.').joinToString("/")}/$name/${version!!}/$name-${version!!}-lockfile.txt"
            URL("$domain/$file")
        }.readText()
    }
}

dependencies {
    //    implementation(platform("kotlin.graphics.platform:source:0.3.3+18"))
    //    implementation(group = "kotlin.graphics.platform" , name = "source", version = "0.3.3+18", classifier = "lockfile", ext = "txt")
    //    implementation("kotlin.graphics.platform:source:0.3.3+18:lockfile@txt")

    val lines = lockfile.split('\n').filter { !it.startsWith('#') }
    project.dependencies {
        constraints {
            for (line in lines) {
                if(settings.verbose.get())
                    println("api($line)")
                add("api", line)
            }
        }
    }
}