package kx

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

interface KxProjectInterface
interface KxSingleProjectInterface : KxProjectInterface
interface KxMultiProjectInterface : KxProjectInterface {
    val modules: List<KxSingleProjectInterface>
}

object KxProject {

    object kool : KxSingleProjectInterface
    object unsigned : KxSingleProjectInterface
    object glm : KxSingleProjectInterface
    object gli : KxSingleProjectInterface
    object gln : KxSingleProjectInterface
    object vkk : KxSingleProjectInterface

    object uno : KxMultiProjectInterface {
        object awt : KxSingleProjectInterface
        object core : KxSingleProjectInterface
        object vk : KxSingleProjectInterface
        override val modules: List<KxSingleProjectInterface> = listOf(awt, core, vk)
    }

    object imgui : KxMultiProjectInterface {
        object core: KxSingleProjectInterface
        object gl: KxSingleProjectInterface
        object glfw: KxSingleProjectInterface
        object openjfx: KxSingleProjectInterface
        object vk: KxSingleProjectInterface
        override val modules: List<KxSingleProjectInterface> = listOf(uno.awt, uno.core, uno.vk)
    }

    object assimp : KxSingleProjectInterface
    object openvr : KxSingleProjectInterface
}

//sealed interface Expr

//enum class KxProject(val subprojects: List<KxProject> = emptyList()) {
//    kool,
//    unsigned,
//    glm,
//    gli,
//    gln,
//    vkk,
//    unoCore,
//    unoAwt,
//    unoVk,
//    uno(listOf(unoAwt, unoCore, unoVk)),
//    imguiCore,
//    imguiGl,
//    imguiGlfw,
//    imguiOpenjfx,
//    imguiVk,
//    imgui(listOf(imguiCore, imguiGl, imguiGlfw, imguiOpenjfx, imguiVk)),
//    assimp,
//    openvr
//}

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

//fun DependencyHandler.kxImplementation(vararg projects: KxProject) = add(false, projects)
//fun DependencyHandler.kxTestImplementation(vararg projects: KxProject) = add(true, projects)
//
//private fun DependencyHandler.add(test: Boolean, projects: Array<out KxProject>) {
//    for (p in projects) {
//        if (p.,p.isEmpty())
//            add(test, p.name.toSnakeCase())
//        else
//            for (subP in p.subprojects)
//                add(test, subP.name.toSnakeCase())
//    }
//}

private fun String.toSnakeCase() = replace(Regex("[A-Z]")) { "-${it.value.decapitalize()}" }

private fun DependencyHandler.implementation(test: Boolean, artifact: String): Dependency? =
    add(if (test) "testImplementation" else "implementation", "kotlin.graphics:$artifact") // { exclude("kx.platform") }

//private fun DependencyHandler.impl(dependencyNotation: String, dependencyConfiguration: Action<ExternalModuleDependency>): ExternalModuleDependency =
//    addDependencyTo(this, "implementation", dependencyNotation, dependencyConfiguration)
//
//private fun DependencyHandler.testImpl(dependencyNotation: String, dependencyConfiguration: Action<ExternalModuleDependency>): ExternalModuleDependency =
//    addDependencyTo(this, "testImplementation", dependencyNotation, dependencyConfiguration)
//
//private fun <T : ModuleDependency> T.exclude(group: String? = null, module: String? = null): T =
//    uncheckedCast(exclude(excludeMapFor(group, module)))

@Suppress("unchecked_cast", "nothing_to_inline")
private inline fun <T> uncheckedCast(obj: Any?): T = obj as T

private fun excludeMapFor(group: String?, module: String?): Map<String, String> =
    mapOfNonNullValuesOf("group" to group, "module" to module)

private fun mapOfNonNullValuesOf(vararg entries: Pair<String, String?>): Map<String, String> =
    mutableMapOf<String, String>().apply {
        for ((k, v) in entries)
            if (v != null)
                put(k, v)
    }