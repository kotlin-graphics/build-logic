package kx

import java.net.URL

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

val dynamicAlignVerbose: Boolean? by project
println(dynamicAlignVerbose)
println(lockfile)

dependencies {
    //    implementation(platform("kotlin.graphics.platform:source:0.3.3+18"))
    //    implementation(group = "kotlin.graphics.platform" , name = "source", version = "0.3.3+18", classifier = "lockfile", ext = "txt")
    //    implementation("kotlin.graphics.platform:source:0.3.3+18:lockfile@txt")

    val lines = lockfile.split('\n').filter { !it.startsWith('#') }
    project.dependencies {
        constraints {
            for (line in lines) {
                if(dynamicAlignVerbose == true)
                    println("api($line)")
                add("api", line)
            }
        }
    }
}