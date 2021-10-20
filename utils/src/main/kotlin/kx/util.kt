package kx

import org.gradle.api.artifacts.dsl.DependencyHandler
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty

interface KxProject {
    val name: String
}

abstract class KxMultiProject : KxProject {
    val modules = ArrayList<KxProject>()

    fun project() = PropertyDelegateProvider { _: Any?, prop ->
        val project = object : KxProject {
            override val name = "${this@KxMultiProject.name}-${prop.name}"
        }
        modules += project
        ReadOnlyProperty<Any?, KxProject> { _, _ -> project }
    }
}

fun project() = PropertyDelegateProvider { _: Any?, prop ->
    val project = object : KxProject {
        override val name = prop.name
    }
    ReadOnlyProperty<Any?, KxProject> { _, _ -> project }
}

val kool by project()
val unsigned by project()
val glm by project()
val gli by project()
val gln by project()
val vkk by project()
object uno : KxMultiProject() {
    override val name = "uno"
    val awt by project()
    val core by project()
    val vk by project()
}
object imgui : KxMultiProject() {
    override val name = "imgui"
    val core by project()
    val gl by project()
    val glfw by project()
    val openjfx by project()
    val vk by project()
}
val assimp by project()
val openvr by project()

//sealed interface Expr

fun DependencyHandler.implementation(vararg projects: KxProject) = implementation(false, projects)

fun DependencyHandler.testImplementation(vararg projects: KxProject) = implementation(true, projects)

//fun DependencyHandler.kxImplementation(vararg projects: KxProject) = add(false, projects)
//fun DependencyHandler.kxTestImplementation(vararg projects: KxProject) = add(true, projects)

//private fun DependencyHandler.add(test: Boolean, projects: Array<out KxProject>) {
//    for (p in projects) {
//        if (p., p.isEmpty())
//        add(test, p.name.toSnakeCase())
//        else
//        for (subP in p.subprojects)
//            add(test, subP.name.toSnakeCase())
//    }
//}

//private fun String.toSnakeCase() = replace(Regex("[A-Z]")) { "-${it.value.decapitalize()}" }

//private fun DependencyHandler.implementation(test: Boolean, artifact: String): Dependency? =
//    add(if (test) "testImplementation" else "implementation", "kotlin.graphics:$artifact") // { exclude("kx.platform") }

private fun DependencyHandler.implementation(test: Boolean, projects: Array<out KxProject>) {
    val config = if (test) "testImplementation" else "implementation"
    val group = "kotlin.graphics"
    for (project in projects)
        when (project) {
            is KxMultiProject ->
                for (module in project.modules)
                    add(config, "$group:${module.name}")
            else -> add(config, "$group:${project.name}")
        }
}

//private fun DependencyHandler.testImpl(dependencyNotation: String, dependencyConfiguration: Action<ExternalModuleDependency>): ExternalModuleDependency =
//    addDependencyTo(this, "testImplementation", dependencyNotation, dependencyConfiguration)

//private fun <T : ModuleDependency> T.exclude(group: String? = null, module: String? = null): T =
//    uncheckedCast(exclude(excludeMapFor(group, module)))
//
//@Suppress("unchecked_cast", "nothing_to_inline")
//private inline fun <T> uncheckedCast(obj: Any?): T = obj as T
//
//private fun excludeMapFor(group: String?, module: String?): Map<String, String> =
//    mapOfNonNullValuesOf("group" to group, "module" to module)
//
//private fun mapOfNonNullValuesOf(vararg entries: Pair<String, String?>): Map<String, String> =
//    mutableMapOf<String, String>().apply {
//        for ((k, v) in entries)
//            if (v != null)
//                put(k, v)
//    }