package kx

import org.gradle.api.Action
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo

enum class KxProject {
    kool, unsigned, glm, gli, gln, vkk, uno, imgui, assimp, openvr;

    val projectName
        get() = when (this) {
            unsigned -> "kotlin-unsigned"
            uno -> "uno-sdk"
            else -> name
        }
}

//object KxBundle {
//    val none = emptyArray<LwjglModules>()
//    val everything = LwjglModules.values()
//    val gettingStarted = arrayOf(assimp, bgfx, glfw, nanovg, nuklear, openal, opengl, par, stb, vulkan)
//    val minimalOpenGL = arrayOf(assimp, glfw, openal, opengl, stb)
//    val minimalOpenGLES = arrayOf(assimp, egl, glfw, openal, opengles, stb)
//    val minimalVulkan = arrayOf(assimp, glfw, openal, stb, vulkan)
//}

//@JvmName("KxImplementation2")
//fun DependencyHandler.kxImplementation(modules: Array<KxProject>) = impl(false, modules)
//
//@JvmName("KxTestImplementation2")
//fun DependencyHandler.kxTestImplementation(modules: Array<KxProject>) = impl(true, modules)

fun DependencyHandler.kxImplementation(vararg projects: KxProject) = add(false, projects as Array<KxProject>)
fun DependencyHandler.kxTestImplementation(vararg projects: KxProject) = add(true, projects as Array<KxProject>)

private fun DependencyHandler.add(test: Boolean, projects: Array<KxProject>) {
    for (p in projects) {
        val group = "com.github.kotlin-graphics"
        val art = p.projectName
        if (test)
            testImpl("$group:$art") { exclude("kx.platform") }
        else
            impl("$group:$art") { exclude("kx.platform") }
    }
}

private fun DependencyHandler.impl(
    dependencyNotation: String,
    dependencyConfiguration: Action<ExternalModuleDependency>
): ExternalModuleDependency = addDependencyTo(
    this, "implementation", dependencyNotation, dependencyConfiguration
) as ExternalModuleDependency

private fun DependencyHandler.testImpl(
    dependencyNotation: String,
    dependencyConfiguration: Action<ExternalModuleDependency>
): ExternalModuleDependency = addDependencyTo(
    this, "testImplementation", dependencyNotation, dependencyConfiguration
) as ExternalModuleDependency

private fun <T : ModuleDependency> T.exclude(group: String? = null, module: String? = null): T =
    uncheckedCast(exclude(excludeMapFor(group, module)))

@Suppress("unchecked_cast", "nothing_to_inline")
private inline fun <T> uncheckedCast(obj: Any?): T =
    obj as T

private fun excludeMapFor(group: String?, module: String?): Map<String, String> =
    mapOfNonNullValuesOf(
        "group" to group,
        "module" to module
    )

private fun mapOfNonNullValuesOf(vararg entries: Pair<String, String?>): Map<String, String> =
    mutableMapOf<String, String>().apply {
        for ((k, v) in entries) {
            if (v != null) {
                put(k, v)
            }
        }
    }